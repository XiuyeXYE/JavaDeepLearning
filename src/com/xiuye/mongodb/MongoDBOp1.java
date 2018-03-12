package com.xiuye.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBOp1 {
	public static void main(String[] args) {
		try{
			//直接能连接mongodb
			MongoClient mongoClient = new MongoClient("localhost",27017);
			log("连接mongodb成功!");

			//准备使用用户账号连接
			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			//mongodb默认不需要用户名和密码就可以登录
			//虽然能连接但是后面的操作会报错!还是创建一个吧
			//数据库必须与用户账号一致,也就是test数据库中创建了用户
			MongoCredential credential = MongoCredential.createScramSha1Credential("xiuye",
					"test", "123456".toCharArray());
			List<MongoCredential> credentials = new ArrayList<>();
			credentials.add(credential);

			mongoClient = new MongoClient(addrs,credentials);
			//数据库的名字也可以是任意的!哪怕不存在,也可以!但不能为空
			//如果没有数据库,会自动创建一个(仅限于直接localhost连接)
			//每个数据库,必须创建用户,才能使用用户账号登录.向下面这样
			//>use test
			//>db.createUser({user:"xiuye",pwd:"123456",roles:[]})
			//>
			MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

			log("获取数据库成功!");
			//重复创建会报错
			//Collection就相当于关系型数据库表
			mongoDatabase.createCollection("Java");
			log("集合创建成功!");


			MongoCollection<Document> collection = mongoDatabase.getCollection("Java");

			//文档相当于数据库表的一条记录
			Document doc = new Document("title","文档")
					.append("content", "文档相当于数据库表的一条记录")
					.append("links", "100")
					.append("key", "1");

			List<Document> docs = new ArrayList<Document>();
			docs.add(doc);

			//插入多个文档(多条记录)
			collection.insertMany(docs);

			log("插入一个文档成功!");

			//查询所有文档
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while(mongoCursor.hasNext()){
				log("得到的文档内容:"+mongoCursor.next().toString());
			}

			//更新文档内容
			collection.updateMany(Filters.eq("links","100"), new Document("$set",new Document("links","333")));

			findIterable = collection.find();
			mongoCursor = findIterable.iterator();
			while(mongoCursor.hasNext()){
				log("更新的文档内容:"+mongoCursor.next().toString());
			}

			//删除一条记录
			collection.deleteMany(Filters.eq("key","1"));

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	static void log(String s){
		System.out.println(s);
	}
}
