package com.inzaa.iot.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig {

	private static String[] dbHosts;
	private static String userName;
	private static String authDB;
	private static String password;

	static {
		dbHosts = new String[] { System.getProperties().getProperty("dbhosts", "localhost:5000") };
		userName = System.getProperties().getProperty("db_username", "admin");
		authDB = System.getProperties().getProperty("db_auth_db", "admin");
		password = System.getProperties().getProperty("db_password", "admin");
	}

	private static final String DB_NAME = "iot_mine";

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(getMongo(userName, password, authDB), DB_NAME);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	private MongoClient getMongo(String userName, String password, String authenticationDB) {
		MongoClient mongoClient = null;

		try {
			ArrayList<ServerAddress> e = new ArrayList<ServerAddress>();

			int timeOut;
			for (int mongoClientOptionsBuilder = 0; mongoClientOptionsBuilder < dbHosts.length; ++mongoClientOptionsBuilder) {
				if (dbHosts[mongoClientOptionsBuilder] != null) {
					String[] credList = dbHosts[mongoClientOptionsBuilder].split(":");
					if (credList.length > 1) {
						String mongoClientOptions = credList[0];
						timeOut = Integer.parseInt(credList[1]);
						ServerAddress aAddress = new ServerAddress(mongoClientOptions, timeOut);
						e.add(aAddress);
					}
				}
			}

			com.mongodb.MongoClientOptions.Builder arg11 = new com.mongodb.MongoClientOptions.Builder();
			arg11.connectionsPerHost(100);
			arg11.connectTimeout(1500);
			timeOut = 600000;
			arg11.socketTimeout(600000);
			MongoClientOptions arg12 = arg11.build();
			ArrayList<MongoCredential> arg13 = new ArrayList<MongoCredential>();
			arg13.add(MongoCredential.createCredential(userName, authenticationDB, password.toCharArray()));
			mongoClient = new MongoClient(e, arg13, arg12);
			if (mongoClient.getReplicaSetStatus() != null && mongoClient.getReplicaSetStatus().getName() != null
					&& dbHosts.length > 1) {
				setDBReadPreference(mongoClient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mongoClient;
	}

	private void setDBReadPreference(MongoClient mongoClient) {
		ReadPreference readPref;
		if ("primary".equalsIgnoreCase("primary")) {
			readPref = ReadPreference.primary();
		} else if ("primarypreferred".equalsIgnoreCase("primary")) {
			readPref = ReadPreference.primaryPreferred();
		} else if ("secondary".equalsIgnoreCase("primary")) {
			readPref = ReadPreference.secondary();
		} else if ("secondaryPreferred".equalsIgnoreCase("primary")) {
			readPref = ReadPreference.secondaryPreferred();
		} else if ("nearest".equalsIgnoreCase("primary")) {
			readPref = ReadPreference.nearest();
		} else {
			readPref = ReadPreference.secondaryPreferred();
		}
		mongoClient.setReadPreference(readPref);
	}

}
