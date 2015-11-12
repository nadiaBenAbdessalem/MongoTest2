package com.mycompany.mongotest2;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class main_window {
  public static void main(String[] args) {

    try {

	/**** Connect to MongoDB ****/
	// Since 2.10.0, uses MongoClient
	MongoClient mongo = new MongoClient("localhost", 27017);

	/**** Get database ****/
	// if database doesn't exists, MongoDB will create it for you
	DB db = mongo.getDB("base");

	/**** Get collection / table from 'testdb' ****/
	// if collection doesn't exists, MongoDB will create it for you
	DBCollection table = db.getCollection("etudiant");

	/**** Insert ****/
	// create a document to store key and value for student1
	BasicDBObject document1 = new BasicDBObject();
	document1.put("name", "nadia");
	document1.put("age", 23);
	document1.put("createdDate", new Date());
	table.insert(document1);
       // create a document1 to store key and value for student2
	BasicDBObject document2 = new BasicDBObject();
	document2.put("name", "imen");
	document2.put("age", 22);
	document2.put("createdDate", new Date());
	table.insert(document2);

	/**** Find and display student1 ****/
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("name", "nadia");

	DBCursor cursor = table.find(searchQuery);

	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}
        
        /**** Find and display student2 ****/
	BasicDBObject searchQuery1 = new BasicDBObject();
	searchQuery.put("name", "imen");

	DBCursor cursor1 = table.find(searchQuery1);

	while (cursor1.hasNext()) {
		System.out.println(cursor1.next());
	}

	/**** Update ****/
	// search document where name="mkyong" and update it with new values
	BasicDBObject query = new BasicDBObject();
	query.put("name", "nadia");

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "nadia-updated");

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);

	/**** Find and display ****/
	BasicDBObject searchQuery2 
	    = new BasicDBObject().append("name", "nadia-updated");

	DBCursor cursor2 = table.find(searchQuery2);

	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}
        /**** delete student****/
       

	BasicDBObject searchQuery3 = new BasicDBObject();
	searchQuery3.put("name", "imen");

	table.remove(searchQuery3);

	/**** Done ****/
	System.out.println("Done");

    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }

  }
}