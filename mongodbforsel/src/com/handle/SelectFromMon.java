package com.handle;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class SelectFromMon {

	public static void main(String[] args) {
		// 1.建立一个Mongo的数据库连接对象
		Mongo mg = new Mongo("127.0.0.1:27017");
		// 查询所有的Database
		for (String name : mg.getDatabaseNames()) {
			System.out.println("dbName: " + name);
		}
		// 2.创建相关数据库的连接
		// DB db = mg.getDB("wiki");
		// // 查询数据库所有的集合
		// for (String name : db.getCollectionNames()) {
		// System.out.println("collectionName: " + name);
		// }

		// DBCollection latest = db.getCollection("latest");

		MongoClient mongoClient = new MongoClient();
		// MongoDatabase db = mongoClient.getDatabase("test");
		// MongoCollection<Document> doc = db.getCollection("data");
		// FindIterable<Document> iter = doc.find();
		// iter.forEach(new Block<Document>() {
		// public void apply(Document _doc) {
		// System.out.println(_doc.toJson());
		// }
		// });
		MongoDatabase db = mongoClient.getDatabase("wiki");
		MongoCollection<Document> doc = db.getCollection("la");
		FindIterable<Document> iter = doc.find(Filters.eq("id", "Q31"));
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				System.out.println(_doc.toJson());
			}
		});
		mg.close();

	}

}
