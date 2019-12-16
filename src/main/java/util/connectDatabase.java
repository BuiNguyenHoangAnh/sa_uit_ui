package util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;

import org.bson.conversions.Bson;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class connectDatabase {
	
	int port_no = 27017;
	String url = "127.0.0.1";
	
	String db_name = "sa_uit_database";
	String db_collection_name = "data";
	
	MongoDatabase db;
	MongoCollection col;
	
	Instant date;
	
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
	
	private int getData(Bson bson, MongoCollection collection) {
		int count = 0;
		
		FindIterable result = collection.find(bson);
		Iterator iterator = result.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			count ++;
		}
		
		return count;
	}
	
	public Instant getLastWeekDate() {
		return Instant.now().minus(6, ChronoUnit.DAYS);
	}
	
	public Instant getYesterdayDate() {
		return Instant.now().minus(1, ChronoUnit.DAYS);
	}
	
	public Instant getLastYearDate() {
		return Instant.now().minus(364, ChronoUnit.DAYS);
	}
	
	public Instant getNow() {
		return Instant.now();
	}
	
	/*
	 * Training
	 */
 	public int countNegTrainingPerDay() {	
		this.date = this.getNow();
		Instant last = this.getLastWeekDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();

		return this.getData(bson, this.col);
	}
	public int countNegTrainingPerMonth() {
		this.date = this.getNow();
		Instant last = this.getLastYearDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	public int countNegTrainingPerHour() {
		this.date = this.getNow();
		Instant yesterday = this.getYesterdayDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", yesterday.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	
	public int countPosTrainingPerDay() {		
		this.date = this.getNow();
		Instant last = this.getLastWeekDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();

		return this.getData(bson, this.col);
	}
	public int countPosTrainingPerMonth() {
		this.date = this.getNow();
		Instant last = this.getLastYearDate();
	
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	public int countPosTrainingPerHour() {
		this.date = this.getNow();
		Instant yesterday = this.getYesterdayDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", yesterday.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	
	/*
	 * Facility
	 */
	public int countNegFacilityPerDay() {		
		this.date = this.getNow();
		Instant last = this.getLastWeekDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();

		return this.getData(bson, this.col);
	}
	public int countNegFacilityPerMonth() {
		this.date = this.getNow();
		Instant last = this.getLastYearDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	public int countNegFacilityPerHour() {
		this.date = this.getNow();
		Instant yesterday = this.getYesterdayDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", this.date.toString()), Filters.gte("time", yesterday.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	
	public int countPosFacilityPerDay() {		
		this.date = this.getNow();
		Instant last = this.getLastWeekDate();
		
		Bson bson = Filters.and(Filters.eq("csvc", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();

		return this.getData(bson, this.col);
	}
	public int countPosFacilityPerMonth() {
		this.date = this.getNow();
		Instant last = this.getLastYearDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", last.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
	public int countPosFacilityPerHour() {
		this.date = this.getNow();
		Instant yesterday = this.getYesterdayDate();
		
		Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "pos"), Filters.lte("time", this.date.toString()), Filters.gte("time", yesterday.toString()));
		
		this.connectDB();
		
		return this.getData(bson, this.col);
	}
}
