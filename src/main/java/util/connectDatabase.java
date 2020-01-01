package util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class connectDatabase {
	
	int port_no = 27017;
	String url = "127.0.0.1";
	
	String db_name = "uit";
	String db_collection_name = "uit.statistic";
	
	MongoDatabase db;
	MongoCollection col;
	
	// Make a connection to the mongoDB server
	private MongoClient getConnection() {
		int port = this.port_no;
		String url = this.url;
		
		MongoClient mongoClientObj = new MongoClient(url, port);
		return mongoClientObj;
	}
	
	private void connectDB() {
		this.db = this.getConnection().getDatabase(this.db_name);
		
		this.col = this.db.getCollection(this.db_collection_name);
	}
	
	public List<Statistic> getDataFromDB() {
		this.connectDB();
		
        List<Statistic> list = new ArrayList<>();
        
        Block<Document> setListBlock = new Block<Document>() {

			@Override
			public void apply(Document t) {
				list.add(new Statistic(t.getString("type"), t.getString("typeDetail"), t.getString("typeSource"), t.getString("posTraining"), t.getString("negTraining"), t.getString("posFacility"), t.getString("negFacility")));
			}
        	
        };
        this.col.find().forEach(setListBlock);;
        
		return list;
	}
}
