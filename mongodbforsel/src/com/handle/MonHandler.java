package com.handle;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.entity.Claims;
import com.entity.Labels;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/MonHandler")
public class MonHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Labels lab;
    private Claims claims;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String mongodbSearch = request.getParameter("mongodbsearch");
		String style1 = request.getParameter("style1");
		System.out.println("查询内容：" + mongodbSearch);
		System.out.println("查询方式：" + style1);
		request.setAttribute("what", mongodbSearch);
		// 1.建立一个Mongo的数据库连接对象
		Mongo mg = new Mongo("127.0.0.1:27017");
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("wiki");

//		FindIterable<Document> iter = doc.find(Filters.eq("id", mongodbSearch));
		long begin = 0;
		long end = 0;
		long begin1 = 0;
		long end1 = 0;
		List listID=new ArrayList<>();
		List listMainsnak = new ArrayList<>();
		List listLanguage=new ArrayList<>();
		switch (style1) {
		case "name"://-------------------------------------------第一个功能查询---------------------------------------------------------------------
			MongoCollection<Document> doc = db.getCollection("labels");	
			MongoCollection<Document> doc2 = db.getCollection("labels");	
			begin = System.currentTimeMillis();
			FindIterable<Document> iter = doc.find(Filters.eq("value", new BasicDBObject("$regex",mongodbSearch)));
			end = System.currentTimeMillis();
			request.setAttribute("time", end - begin);
			
			doc2.createIndex(new Document("value",1));
			begin1 = System.currentTimeMillis();
			doc2.find(Filters.eq("value", new BasicDBObject("$regex",mongodbSearch)));
			end1 = System.currentTimeMillis();
			request.setAttribute("time", end1 - begin1);
			
//			System.out.println("开始时间：begin"+begin);
//			System.out.println("结束时间：end"+begin);
//			Set listID = new HashSet<>();
//			Set listLanguage = new HashSet<>();
//			Set listValue = new HashSet<>();
			
			
			List listValue=new ArrayList<>();
//			request.setAttribute("results", null);
			iter.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					
					String res = _doc.toJson();
					System.out.println("res name:"+res);
					Map<String,Object> map = JSON.parseObject(res); 
//					for (Entry<String, Object> entry : map.entrySet()) {
//						System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
//					String[] listLanguage = new String[map.size()];
//					for(int i=0;i<listLanguage.length;i++){
//						listLanguage[i] = (String) map.get("language");
//					}
					listID.add(map.get("id"));
					listLanguage.add(map.get("language"));
					listValue.add(map.get("value"));
//					System.out.println(listLanguage);

//					lab = new Labels();
//					lab.setId("id:"+(String) map.get("id"));
//					lab.setLanguage("language:"+(String) map.get("language"));
					
//					lab.setValue("value:"+(String) map.get("value"));
					
				}
			});
			request.setAttribute("des1", "查询描述：给定名称，返回与名称匹配的所有实体。(name——>entity)");
			request.setAttribute("id", "id");
			request.setAttribute("language", "language");
			request.setAttribute("value", "value");
			lab = new Labels();
			lab.setId(StringUtils.strip(listID.toString(),"[]"));
			lab.setLanguage(StringUtils.strip(listLanguage.toString(),"[]"));
			lab.setValue(StringUtils.strip(listValue.toString(),"[]"));
			if(listValue.size()==0){
				request.setAttribute("mess2", "抱歉！查无此相关内容。");
				
			}
			request.setAttribute("results", lab);
			break;
		case "id"://-------------------------------------------第二个功能查询---------------------------------------------------------------------
			MongoCollection<Document> docId = db.getCollection("claims");
			MongoCollection<Document> docId2 = db.getCollection("claims");
			begin = System.currentTimeMillis();
			FindIterable<Document> iterId = docId.find(Filters.eq("id", mongodbSearch));
			end = System.currentTimeMillis();
			request.setAttribute("time", end - begin);
			
			docId2.createIndex(new Document("id", 1));
			begin1 = System.currentTimeMillis();
			docId2.find(Filters.eq("id", mongodbSearch));
			end1 = System.currentTimeMillis();
			request.setAttribute("time1", end1 - begin1);
			System.out.println("begin-------"+begin);
			System.out.println("end-------"+end);
			System.out.println("time--------"+ (end - begin));
			System.out.println("begin1-------"+begin1);
			System.out.println("end1-------"+end1);
			System.out.println("time1--------"+ (end1 - begin1));
//			request.setAttribute("result", null);
//			List listEntities = new ArrayList<>();
			iterId.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					String res = _doc.toJson();
//					System.out.println("res id:"+res);
					Map<String,Object> map = JSON.parseObject(res);
//					for (Entry<String, Object> entry : map.entrySet()) {
//						System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
					listID.add(map.get("id"));
					Map<String,Object> map1 = JSON.parseObject(map.get("mainsnak").toString());
					//datavalue
//					Map<String,Object> map2 = JSON.parseObject(map.get("datavalue").toString());
//					Map<String,Object> map3 = JSON.parseObject(map2.get("value").toString());
//					Map<String,Object> map4 = JSON.parseObject(map3.get("id").toString());
					listMainsnak.add(map1.get("property"));
//					listEntities.add(map3.get("id"));
				}
			});
			request.setAttribute("des2", "查询描述：给定一个实体，返回给定实体所属的所有先前类别（实例和子类）。(id——>pre-catego)");
			request.setAttribute("idp", "id");
			request.setAttribute("property", "property");
			claims = new Claims();
			claims.setId(StringUtils.strip(listID.toString(),"[]"));
//			request.setAttribute("ide", "id");
			claims.setProperty(StringUtils.strip(listMainsnak.toString(),"[]"));
//			claims.setId(StringUtils.strip(listEntities.toString(),"[]"));
//			Object property = listMainsnak.get(0);
//			System.out.println("property:"+property);
			if(listMainsnak.size()==0){
				request.setAttribute("mess1", "抱歉！查无此相关内容。");
				
			}
			request.setAttribute("result", claims);
			break;
		case "entity"://-------------------------------------------第三个功能查询---------------------------------------------------------------------
			MongoCollection<Document> docEntity = db.getCollection("claims");
			MongoCollection<Document> docEntity2 = db.getCollection("claims");
			begin = System.currentTimeMillis();
			FindIterable<Document> iterEntity = docEntity.find(Filters.eq("id", mongodbSearch));
			end = System.currentTimeMillis();
			request.setAttribute("time", end - begin);
			
			docEntity2.createIndex(new Document("id",1));
			begin1 = System.currentTimeMillis();
			docEntity2.find(Filters.eq("id", mongodbSearch));
			end1 = System.currentTimeMillis();
			request.setAttribute("time", end1 - begin1);
			
			List listEntities = new ArrayList<>();
//			request.setAttribute("resultEntity", null);
			iterEntity.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					String res = _doc.toJson();
//					System.out.println("res id:"+res);
					Map<String,Object> map = JSON.parseObject(res);
//					for (Entry<String, Object> entry : map.entrySet()) {
//						System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
					listID.add(map.get("id"));
					Map<String,Object> map1 = JSON.parseObject(map.get("mainsnak").toString());
//					for (Entry<String, Object> entry : map1.entrySet()) {
//						System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
					if(map1.get("datatype").equals("wikibase-item") && map1.get("datavalue")!=null){
//						System.out.println("values"+map1.values());
						Map<String,Object> map2 = JSON.parseObject(map1.get("datavalue").toString());
//						Map<String,Object> map2 = JSON.parseObject(StringUtils.strip(map1.values().toString(),"[]"));
//						for (Entry<String, Object> entry : map2.entrySet()) {
//							System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//						}
						
						Map<String,Object> map3 = JSON.parseObject(map2.get("value").toString());
						listEntities.add(map3.get("id"));
						
					}
//					if (findEntities != null && findEntities.size() >= 2) {
//						System.out.println("list中第二个元素 " + findEntities.get(1));
//					}
					
				}
			});
			request.setAttribute("des3", "查询描述：给定一个实体，在一个语句中返回与该实体共同出现的所有实体。(id——>all-entity)");
			claims = new Claims();
//			claims.setId(StringUtils.strip(listID.toString(),"[]"));
			request.setAttribute("ide", "Entity");
			claims.setId(StringUtils.strip(listEntities.toString(),"[]"));
			if(listEntities.size()==0){
				request.setAttribute("mess", "抱歉！查无此相关内容。");
				System.out.println("resultEntity--null"+listEntities);
			}
			request.setAttribute("resultEntity", claims);
			break;
		case "ownpro"://-------------------------------------------第四个功能查询---------------------------------------------------------------------
			MongoCollection<Document> docOwnpro = db.getCollection("claims");
			MongoCollection<Document> docOwnpro2 = db.getCollection("claims");
			begin = System.currentTimeMillis();
			FindIterable<Document> iterOwnpro = docOwnpro.find(Filters.eq("id", mongodbSearch));
			end = System.currentTimeMillis();
			request.setAttribute("time", end - begin);
			
			docOwnpro2.createIndex(new Document("id", 1));
			begin1 = System.currentTimeMillis();
			docOwnpro2.find(Filters.eq("id", mongodbSearch));
			end1 = System.currentTimeMillis();
			request.setAttribute("time", end1 - begin1);
			System.out.println("begin-------"+begin);
			System.out.println("end-------"+end);
			System.out.println("time--------"+ (end - begin));
			System.out.println("begin1-------"+begin1);
			System.out.println("end1-------"+end1);
			System.out.println("time1--------"+ (end1 - begin1));
			List listOwnpro = new ArrayList<>();
//			request.setAttribute("resultOwnpro", null);
			iterOwnpro.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					String res = _doc.toJson();
//					System.out.println("res id:"+res);
					Map<String,Object> map = JSON.parseObject(res);
//					for (Entry<String, Object> entry : map.entrySet()) {
//						System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
					listID.add(map.get("id"));
					Map<String,Object> map1 = JSON.parseObject(map.get("mainsnak").toString());
//					for (Entry<String, Object> entry : map1.entrySet()) {
//						System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//					}
					
					if(map1.get("datavalue")!=null){
//						System.out.println("values"+map1.values());
						listMainsnak.add(map1.get("property"));
						Map<String,Object> map2 = JSON.parseObject(map1.get("datavalue").toString());
//						Map<String,Object> map2 = JSON.parseObject(StringUtils.strip(map1.values().toString(),"[]"));
//						for (Entry<String, Object> entry : map2.entrySet()) {
//							System.out.println(" key= " + entry.getKey() + " and value= " + entry.getValue());
//						}
						
//						Map<String,Object> map3 = JSON.parseObject(map2.get("value").toString());
						listOwnpro.add(map2.get("value"));
						
					}
					
				}
			});
			for(int i=0;i<listOwnpro.size();i++){
				listOwnpro.toString().replaceAll(",", "");
				System.out.println(listOwnpro.get(i));
				
			}
			request.setAttribute("des4", "查询描述：给定一个实体，返回它拥有的所有属性和值。(id——>ownpro)");
			request.setAttribute("dataproperty", "property");
			request.setAttribute("datavalue", "data_value");
			claims = new Claims();
//			claims.setId(StringUtils.strip(listID.toString(),"[]"));
			
//			claims.setId(StringUtils.strip(listOwnpro.toString(),"[]"));//value 借用ID
			System.out.println("IDvalue: "+claims.getId());
			claims.setProperty(StringUtils.strip(listMainsnak.toString(),"[]"));//属性
			request.setAttribute("resOwnpro",listOwnpro);
			request.setAttribute("resultOwnpro", claims);
			break;
		case "qaq"://-------------------------------------------第五个功能查询---------------------------------------------------------------------
			MongoCollection<Document> docQaq = db.getCollection("labels");	
			MongoCollection<Document> docQaq2 = db.getCollection("labels");
			begin = System.currentTimeMillis();
			FindIterable<Document> iterQaq = docQaq.find(Filters.eq("value", new BasicDBObject("$regex",mongodbSearch)));		
//			request.setAttribute("time", end - begin);
			
			docQaq2.createIndex(new Document("value", 1));
			begin1 = System.currentTimeMillis();
			docQaq2.find(Filters.eq("value", new BasicDBObject("$regex",mongodbSearch)));
//			end1 = System.currentTimeMillis();
//			request.setAttribute("time1", end1 - begin1);
//			System.out.println("begin-------"+begin);
//			System.out.println("end-------"+end);
//			System.out.println("time--------"+ (end - begin));
//			System.out.println("begin1-------"+begin1);
//			System.out.println("end1-------"+end1);
//			System.out.println("time1--------"+ (end1 - begin1));
//			
			List valueList = new ArrayList<>();
			List newIdList = new ArrayList<>();
			System.out.println("valueList为空"+valueList);
			iterQaq.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					String res = _doc.toJson();
					System.out.println("res id:"+res);
					Map<String,Object> map = JSON.parseObject(res);
					listID.add(map.get("id"));
					listLanguage.add(map.get("language"));
				}
			});
			MongoCollection<Document> docQaq1 = db.getCollection("descriptions");
			//多条件查询需要先定义一个BasicDBObject数组来存储多个条件
//			System.out.println(" listID.get(0)"+ listID.get(0));
			List<BasicDBObject> objects = new ArrayList<BasicDBObject>();
			MongoCursor cursor = null;
			BasicDBObject query= new BasicDBObject();
			query.put("id", new BasicDBObject("$in", listID));
//			BasicDBObject query1= new BasicDBObject();
			query.put("language", new BasicDBObject("$in", listLanguage));
			FindIterable find = docQaq1.find(query);
			end = System.currentTimeMillis();
			request.setAttribute("time", end - begin);
			end1 = System.currentTimeMillis();
			request.setAttribute("time1", end1 - begin1);
			find.forEach(new Block<Document>() {
				public void apply(Document _doc) {
					String res = _doc.toJson();
					System.out.println("res id---:"+res);
					Map<String,Object> map = JSON.parseObject(res);
					newIdList.add(map.get("id"));
					valueList.add(map.get("value"));
				}
			}
					);
			
//			BasicDBObject[] array ={
//					new BasicDBObject("id",new BasicDBObject("$nin", listID.get(0))),
//					new BasicDBObject("language",new BasicDBObject("$nin", listLanguage.get(0)))
//			};
//			BasicDBObject cond = new BasicDBObject();
//            cond.put("$and", array);
//            DBObject match = new BasicDBObject("$match", cond);
//            if(match!=null){
//            	System.out.print(match.get("id") + "-->");
////				System.out.print(match.get("language") + "-->");
////				System.out.println(match.get("value"));
//            }
//			for(int i=0;i<listID.size();i++){
//				objects.add(new BasicDBObject("id",new BasicDBObject("$in", listID)));//listID.get(0)
//				objects.add(new BasicDBObject("language",new BasicDBObject("$in", listLanguage)));
////				System.out.println("objects"+objects);
//				query = new BasicDBObject();
//				query.put("$and",objects);
//				cursor = docQaq1.find(query).iterator();
//				while (cursor.hasNext()) {
//					// System.out.println("cursor.next():"+cursor.next());
//					DBObject object = (DBObject) cursor.next();
//					System.out.print(object.get("id") + "-->");
//					System.out.print(object.get("language") + "-->");
//					System.out.println(object.get("value"));
//				}
//			}
			
			
			//错误想法
//			FindIterable<Document> iterQaq2 = docQaq1.find(Filters.and(Filters.eq("id", listID),Filters.eq("language",listLanguage)));
//			System.out.println(Filters.and(Filters.eq("id", listID),Filters.eq("language",listLanguage)));
			
//			FindIterable<Document> iterQaq2 = docQaq1.find(Filters.and(Filters.eq("id", listID)));
//			Bson condition = Filters.and(Filters.eq("id", listID),Filters.eq("language",listLanguage));
//			System.out.println("condition "+condition);
		
			
//			iterQaq2.forEach(new Block<Document>() {
//				public void apply(Document _doc) {
//					String res = _doc.toJson();
//					System.out.println("res id:"+res);
//					Map<String,Object> map = JSON.parseObject(res);
//					valueList.add(map.get("value"));
//				}
//			});
//			for(int i=0;i<valueList.size();i++){
//				System.out.println("valueList"+valueList.get(i));
//			}
			request.setAttribute("des5", "查询描述：提问和回答。(question——>answer)");
			request.setAttribute("idQaq", "id");
			request.setAttribute("valueQaq", "descriptions");
			request.setAttribute("idQaqList", newIdList);
			if(valueList.size()==0){
				request.setAttribute("valueQaqList", null);
			}
			request.setAttribute("valueQaqList", valueList);
//			cursor1.close();
			break;
		case "qaq1"://-------------------------------------------第六个功能查询---------------------------------------------------------------------
//			System.out.println("查询内容：" + mongodbSearch);
//			URLEncoder.encode(mongodbSearch, "UTF-8");
//			System.out.println(URLEncoder.encode(mongodbSearch, "UTF-8"));
			String url ="";
			String html = "";
			CloseableHttpResponse resp = null;
			CharSequence first = mongodbSearch.subSequence(0, 1);
			if("Q".equals(first)){
//				System.out.println("first"+first);
				url = "https://www.wikidata.org/wiki/"+mongodbSearch+"";
				String[] temp = mongodbSearch.split("Q");
//				System.out.println(temp[1]+"----");
				if(temp[1].matches("[0-9]{1,}")){
					response.setContentType("text/html;charset=UTF-8");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
				    response.setHeader("Location", url); 
				}else{
			        url = "https://www.wikidata.org/w/index.php?search="+URLEncoder.encode(mongodbSearch, "UTF-8")+"&ns0=1&ns120=1";
			        System.out.println(" url "+url);
				}
				
			}else{
				
		        url = "https://www.wikidata.org/w/index.php?search="+URLEncoder.encode(mongodbSearch, "UTF-8")+"&ns0=1&ns120=1";
		        System.out.println(" *url* "+url);
				
			}
			CloseableHttpClient client = HttpClients.createDefault();
//			HttpPut httpPut = new HttpPut(url);
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-type", "text/html; charset=UTF-8");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
			resp = client.execute(httpGet);
			HttpEntity entity = resp.getEntity();
	        if (entity != null) {
	            html = EntityUtils.toString(entity,"utf-8");
	        }
	        EntityUtils.consume(entity);
	        resp.close();
	        System.out.println("html:"+html);
	        org.jsoup.nodes.Document doc1 = Jsoup.parse(html);
	        Elements rows =null;
	        List searchResultList = new ArrayList<>();
	        List searchResultList1 = new ArrayList<>();
	        request.setAttribute("sl1", null);
			if (!"Q".equals(first)) {
				rows = doc1.select("ul[class=mw-search-results]").get(0).select("li");				
				if (rows.size() == 1) {
					System.out.println("没有结果");

				}else{
					System.out.println("查询结果：");
//					Element row = rows.get(1);
					for(int i =0;i< rows.size();i++){
						Element row = rows.get(i);
						String s = row.select("a").get(0).text();
						String[] temp = s.split("Q");
//						System.out.println(temp[0]+"****"+temp[1]);
						temp[0] = temp[0].substring(0,temp[0].length() - 1);
						temp[1] = temp[1].substring(0,temp[1].length() - 1);
						System.out.println("temp[1]"+temp[1]);
						searchResultList.add(temp[0]);
						searchResultList1.add(temp[1]);
//						searchResultList.add(temp[0]+"Q"+"https://www.wikidata.org/wiki/");
//						System.out.println("查询结果:" + row.select("a").get(0).text());
					}
				}
			}
	        
//	        JSONObject ob = JSON.parseObject(body);
//	        String ss = ob.getString("data");
//			System.out.print("ss:"+ss);
			request.setAttribute("des6", "查询描述：提问和回答(结果为在wikidata上查询到的前20个的相关信息)。(question——>wiki)");
			request.setAttribute("la", "label");
	        request.setAttribute("link", "link");
	        request.setAttribute("sl", searchResultList);
	        request.setAttribute("sl1", searchResultList1);
			break;
		}
//		long begin = System.currentTimeMillis();
//		FindIterable<Document> iter = doc.find(Filters.eq("language", mongodbSearch));
//		FindIterable<Document> iter = doc.find(Filters.eq("value", mongodbSearch));//.explain("executionStats")
//		long end = System.currentTimeMillis();
//		long time = end - begin;
//		System.out.println("FileWriter执行耗时:" + time + " 毫秒");
//		request.setAttribute("time", time);
//		iter.forEach(new Block<Document>() {
//			public void apply(Document _doc) {
//				System.out.println(_doc.toJson());
//				String res = _doc.toJson();
//				
//				System.out.println("res:"+res);
//				request.setAttribute("results", _doc.toJson());
//			}
//		});
		
		mg.close();
		
		request.getRequestDispatcher("selectformon.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
