package util;

import com.mongodb.MongoClient;

public class connectDatabase {
	private static MongoClient getConnection() {
		int port = 27017;
		String url = "127.0.0.1";
		
		MongoClient mongoClientObj = new MongoClient(url, port);
		return mongoClientObj;
	}
}
