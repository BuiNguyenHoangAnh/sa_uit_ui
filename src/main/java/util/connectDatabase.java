package util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class connectDatabase {
	
	static int day = 7;
	static int month = 12;
	static int hour = 24;
	
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
 	public int[] countNegTrainingPerDay() {	
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[day];
		
		this.connectDB();
		
		for (int i = 0; i <= day-1; i++) {
			last = this.date.minus(i, ChronoUnit.DAYS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.DAYS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countNegTrainingPerMonth() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[month];
		
		this.connectDB();
		
		for (int i = 0; i <= month-1; i++) {
			last = this.date.minus(i, ChronoUnit.MONTHS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.MONTHS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countNegTrainingPerHour() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[hour];
		
		this.connectDB();
		
		for (int i = 0; i <= hour-1; i++) {
			last = this.date.minus(i, ChronoUnit.HOURS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.HOURS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	
	public int[] countPosTrainingPerDay() {		
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[day];
		
		this.connectDB();
		
		for (int i = 0; i <= day-1; i++) {
			last = this.date.minus(i, ChronoUnit.DAYS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.DAYS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countPosTrainingPerMonth() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[month];
		
		this.connectDB();
		
		for (int i = 0; i <= month-1; i++) {
			last = this.date.minus(i, ChronoUnit.MONTHS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.MONTHS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countPosTrainingPerHour() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[hour];
		
		this.connectDB();
		
		for (int i = 0; i <= hour-1; i++) {
			last = this.date.minus(i, ChronoUnit.HOURS);
			Bson bson = Filters.and(Filters.eq("aspect", "dt"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.HOURS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	
	/*
	 * Facility
	 */
	public int[] countNegFacilityPerDay() {		
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[day];
		
		this.connectDB();
		
		for (int i = 0; i <= day-1; i++) {
			last = this.date.minus(i, ChronoUnit.DAYS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.DAYS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countNegFacilityPerMonth() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[month];
		
		this.connectDB();
		
		for (int i = 0; i <= month-1; i++) {
			last = this.date.minus(i, ChronoUnit.MONTHS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.MONTHS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countNegFacilityPerHour() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[hour];
		
		this.connectDB();
		
		for (int i = 0; i <= hour-1; i++) {
			last = this.date.minus(i, ChronoUnit.HOURS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "neg"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.HOURS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	
	public int[] countPosFacilityPerDay() {		
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[day];
		
		this.connectDB();
		
		for (int i = 0; i <= day-1; i++) {
			last = this.date.minus(i, ChronoUnit.DAYS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.DAYS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countPosFacilityPerMonth() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[month];
		
		this.connectDB();
		
		for (int i = 0; i <= month-1; i++) {
			last = this.date.minus(i, ChronoUnit.MONTHS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.MONTHS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
	public int[] countPosFacilityPerHour() {
		this.date = this.getNow();
		Instant last;
		
		int[] count = new int[hour];
		
		this.connectDB();
		
		for (int i = 0; i <= hour-1; i++) {
			last = this.date.minus(i, ChronoUnit.HOURS);
			Bson bson = Filters.and(Filters.eq("aspect", "csvc"), Filters.eq("sentiment", "pos"), Filters.lte("time", last.toString()), Filters.gte("time", last.minus(1, ChronoUnit.HOURS).toString()));
			
			count[i] = this.getData(bson, this.col);
		}

		return count;
	}
}
