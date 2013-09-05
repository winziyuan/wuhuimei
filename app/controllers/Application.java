package controllers;

import static play.data.Form.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import model.user;


import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.bson.types.ObjectId;




import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;



import play.data.Form;
import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.hello;
import views.html.index;

public class Application extends Controller {
    
    /**
     * Describes the hello form.
     */
    public static class Hello {
        @Required public String name;
        @Required @Min(1) @Max(100) public Integer repeat;
        public String color;
    } 
    
    // -- Actions
  
    
    
    
    /**
     * mongodb
     * @return
     */
    //@Test
    public void testmongo(){
    	try {
			MongoClient mongoClient = new MongoClient( "localhost" , 28031 );
			DB db = mongoClient.getDB( "test" );
			Set<String> colls = db.getCollectionNames();

			for (String s : colls) {
			    System.out.println(s);
			}
			
			DBCollection coll = db.getCollection("user");
			//insert 
/*			long f=System.currentTimeMillis();
			List<DBObject> list=new ArrayList<>();
			for (int i = 500000; i < 500100; i++) {
				BasicDBObject obj=new BasicDBObject().
						append("name", "java"+String.valueOf(i)).
						append("age", i);
				list.add(obj);
			}
			WriteResult re=coll.insert(list);
			System.out.println("n========="+re.getN());
			System.out.println("error========="+re.getError());
			long l=System.currentTimeMillis();
			System.out.println("tatal time="+(l-f));*/
			//^^^^^^^^^ tatal time=158
			
			/////// query
			//findone
			coll.setObjectClass(user.class);
			//DBObject myDoc = coll.findOne();
			user myDoc = (user) coll.findOne();
			//System.out.println(myDoc.getId());
			//System.out.println(myDoc.getName());
			
			
			
			
			//count tatal time====17
			/*long count_f=System.currentTimeMillis();
			System.out.println(coll.count());
			long count_l=System.currentTimeMillis();
			System.out.println("tatal time===="+(count_l-count_f)); 
			// q1 "name", "java500000" tatal time====122
			long q1_f=System.currentTimeMillis();
			BasicDBObject query = new BasicDBObject("name", "java500000");
            
			DBCursor cursor= coll.find(query);

			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			long q1_l=System.currentTimeMillis();
			System.out.println("tatal time===="+(q1_l-q1_f));*/
			
			
			// show indexs
			/*DBCollection coll_index = db.getCollection("system.indexes");
            BasicDBObject query = new BasicDBObject();
            
			DBCursor cursor= coll_index.find(query);

			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}*/
			// q2 use index "name", "java500000" tatal time====5
			/*long q2_f=System.currentTimeMillis();
			BasicDBObject query = new BasicDBObject("age", 500002);
            
			DBCursor cursor= coll.find(query);

			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			long q2_l=System.currentTimeMillis();
			System.out.println("tatal time===="+(q2_l-q2_f));*/
			// get indexs
			/*List<DBObject> list = coll.getIndexInfo();

			for (DBObject o : list) {
			   System.out.println(o);
			}*/
			// get database
			/*for (String s : mongoClient.getDatabaseNames()) {
			   System.out.println(s);
			}*/
			
			////// gridfs save
			/*//DB imagedb = mongoClient.getDB( "image" );
			   DB db2 = mongoClient.getDB("file");
			   GridFS pdf = new GridFS(db2, "image");
			
			try {
				System.out.println(new File("/home/xxc/Erlang程序设计中文版.pdf").isFile()); 
				GridFSInputFile file=pdf.createFile(new File("/home/xxc/Erlang程序设计中文版.pdf"));
				BasicDBObject basic=new BasicDBObject().append("test", "ttttttt");
				file.setMetaData(basic);
				file.save();
				//GridFSInputFile f=
				//System.out.println(f.getId().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			// collections save image  tatal time=98811
		/*	DB filedb = mongoClient.getDB( "file" );
			DBCollection image=filedb.getCollection("image");
			long image_f=System.currentTimeMillis();
			List<DBObject> list=new ArrayList<>();
			File file=new File("/home/xxc/1.jpg");
			 byte[] files=null;
			try {
				FileInputStream input = new FileInputStream(file);
				 files = new byte[input.available()];
				  while (input.read(files)>0) {
					    input.read(files);
					   }
				input.close();
			} catch (FileNotFoundException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 1; i < 10000; i++) {
				
				BasicDBObject obj=new BasicDBObject().
						append("filename", file.getName()).
						append("autor", "xiang").
						append("time", new Date().toLocaleString()).
						append("file", files);
				        
				list.add(obj);
			}
			WriteResult re=image.insert(list);
			System.out.println("n========="+re.getN());
			System.out.println("error========="+re.getError());
			long image_l=System.currentTimeMillis();
			System.out.println("tatal time="+(image_l-image_f));*/
			
			
			
			
			
			
			//read 
			/*DB filedb = mongoClient.getDB( "file" );
			DBCollection coll_index = filedb.getCollection("image");
           // BasicDBObject query = new BasicDBObject();
            
			//DBCursor cursor= coll_index.find(query);
           // System.out.println( coll_index.getCount()); 
			try {
				DBObject obj=coll_index.findOne();
				byte[] bytes=(byte[])obj.get("file");
				new File("/home/xxc/mongo/image/").mkdirs();
				File f=new File("/home/xxc/mongo/image/"+obj.get("filename"));				
				FileOutputStream out=new FileOutputStream(f);
				out.write(bytes);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next().get("filename"));
			       System.out.println(cursor.next().get("time"));
			       System.out.println("-------------------------------");
			   }
			} finally {
			   cursor.close();
			}*/
			
			
			
			
			
			
			
			
			
		   //gridfs	
		/*	   try {
				Mongo mongo = new Mongo();
				   DB db2 = mongoClient.getDB("file");
				   GridFS fs = new GridFS(db2, "image");
				   File file = new File("/home/xxc/Erlang程序设计中文版.pdf");
				   
				   InputStream input = new FileInputStream(file);
				   GridFSInputFile fsFile = fs.createFile(input);
				 
				   fsFile.setFilename(file.getName());
				   fsFile.save();
				   input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			/// gridfs list
			/*DB db2 = mongoClient.getDB("file");
			   GridFS pdf = new GridFS(db2, "image");
			List<GridFSDBFile> list=pdf.find( new BasicDBObject());
			System.out.println(list.size());
			new File("/home/xxc/gridfs/").mkdirs();
			for(GridFSDBFile gf:list){
				System.out.println("gf.getFilename()="+gf.getFilename());
				System.out.println("gf.numChunks()="+gf.numChunks());
				System.out.println("gf.getId()="+gf.getId().toString());
				try {
					gf.writeTo(new File("/home/xxc/gridfs/"+gf.getId().toString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}*/
			
			
			
			
			
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
  
    
    
    
    
    public static BasicDBObject file2object(FilePart part,Map<String, String[]> map){
    	
    	  String filename=part.getFilename();
    	  File file=part.getFile();
    	  String con_type=part.getContentType();
		   BasicDBObject obj=null;
		try {
			if (file.isFile()) {
				
				byte[] bytes=IOUtils.toByteArray(new FileInputStream(file));
				 obj=new BasicDBObject(map).
						append("_id", new ObjectId()).
						append("filename", filename).
						append("content-type", con_type).
						append("autor", "xiang").
						append("time", new Date().toLocaleString()).
						append("byte", bytes);
			}
			//GridFSInputFile f=
			//System.out.println(f.getId().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(obj);
		return obj;
    }
    public static MongoClient getmongoclient() throws UnknownHostException{
    	return new MongoClient( "localhost" , 28031 );
    }
    
    public static Result filet(){
    	MongoClient client=null;
    	DBCollection image=null;
    	try {
			client=getmongoclient();
			 DB db2 = client.getDB("file");
	    	   image = db2.getCollection("pic");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	List<DBObject> list=new ArrayList<>();
    	MultipartFormData body = request().body().asMultipartFormData();
    	Map<String, String[]> map=body.asFormUrlEncoded();
    	List<FilePart> filelist=body.getFiles();
    	List<String> ids=new ArrayList<>();
    	
    	for(FilePart file:filelist){
    		BasicDBObject obj=file2object(file,map);
    		list.add(obj);
    		
    		ObjectId id = (ObjectId)obj.get( "_id" );
    		//System.out.println("id="+ id); 
    		ids.add(id.toString());
    	}
    	image.insert(list);
    	JSONArray array=new JSONArray();
    	array.addAll(ids);
    	System.out.println(array.toJSONString());
    	return ok("<html><head><script>window.parent.success_callback('"+ array.toJSONString()+ "');</script></head><body></body></html>").as("text/html");
    }
    public static DBObject findone(String id){
    	MongoClient client=null;
    	DBCollection image=null;
    	try {
			client=getmongoclient();
			 DB db2 = client.getDB("file");
	    	   image = db2.getCollection("pic");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	BasicDBObject obj=new BasicDBObject().
					append("_id", new ObjectId(id)); 
					
    	
    	return image.findOne(obj);
    }
    
    
    
    public static Result r_filet(String id) {
    	byte[] b=null;

    	DBObject obj=findone(id); 
    	if (obj!=null) {
			
    		Iterator entries = obj.toMap().entrySet().iterator();
    		while (entries.hasNext()) {
    			Entry thisEntry = (Entry) entries.next();
    			Object key = thisEntry.getKey();
    			Object value = thisEntry.getValue();
    			if(key.equals("byte")){
    				b=(byte[]) value;
    			}else{
    				System.out.println("key============"+key);
    				System.out.println("value=========="+value);
    				
    			}
    			
    		}
    		
		}
    	//response().setContentType("application/x-download");  
    	if (b!=null) {
    		//response().setHeader("Content-disposition","attachment; filename="+obj.get("_id")); 
    		//System.out.println(obj.get("filename"));
    		
    		response().setHeader("Content-Type",obj.get("content-type").toString());
    		response().setHeader("Content-disposition","filename="+obj.get("filename"));
    		
    		return ok(b);
		}else{
			return ok();
		}
    }
    
    
    
    
    
    
    /**
     * Handles the form submission.
     */
    public static Result socv() {
   
       
        return ok(views.html.socv.render());
    }
    
    
    // test websocket
    public static WebSocket<String> soct() {
    	return new WebSocket<String>() {
    	      
    	    // Called when the Websocket Handshake is done.
    	    public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
    	      
    	      // For each event received on the socket,
    	    	
    	
    	      in.onMessage(new Callback<String>() {
    	         public void invoke(String event) {
    	             
    	           // Log events to the console
    	           System.out.println(event); 
    	             
    	         } 
    	      });
    	      
    	      // When the socket is closed.
    	      in.onClose(new Callback0() {
    	         public void invoke() {
    	             
    	           System.out.println("Disconnected");
    	             
    	         }
    	      });
    	      
    	      // Send a single 'Hello!' message
    	      out.write("Hello!");
    	      
    	    }};
    	    
    	
    	}
    
    
    
    public static Result msgt() {
    	

	    HttpResponse httpResponse=null;
		try {
			HttpClient httpClient = new DefaultHttpClient(); 
			HttpPost post=new HttpPost("http://utf8.sms.webchinese.cn");
			
			post.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码	
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			
			formParams.add(new BasicNameValuePair("Uid", "winziyuan"));
			formParams.add(new BasicNameValuePair("Key", "362281"));
			formParams.add(new BasicNameValuePair("smsMob","15811027039"));
			formParams.add(new BasicNameValuePair("smsText","this code:1200"));
			
			post.setEntity(new UrlEncodedFormEntity(formParams, HTTP.UTF_8));
			httpResponse = httpClient.execute(post);
			Header[]  headers = httpResponse.getAllHeaders();
			int	statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode:"+statusCode);
			for(Header h : headers)
			{
				System.out.println(h.getName()+" "+h.getValue());
			}
			String result=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			
			System.out.println("===result=====>"+result);
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    


		
    	
    	return ok();
    }
    
    
    public static Result emailt() {
    	String re="not send";
    	try {
    	/*	
    	SimpleEmail email = new SimpleEmail();
    	  email.setHostName("smtp.163.com");
    		email.addTo("winziyuan@gmail.com", "winziyuan");
    		email.setFrom("winziyuan@163.com", "Me");
    	    email.setAuthentication("winziyuan@163.com", "xiang@123"); 
    		email.setMsg("亲爱的，你好");
    		email.setSubject("这是一封邮件");
    		email.setCharset("UTF-8");
			re=email.send();*/
    		EmailAttachment attachment = new EmailAttachment();
    		attachment.setPath("/home/xxc/play.pdf");
    		
    		attachment.setDisposition(EmailAttachment.ATTACHMENT);
    		attachment.setDescription("image");
    		attachment.setName(MimeUtility.encodeText("play框架.pdf"));	
    		 
    		// Create the email message
    		InternetAddress i=new InternetAddress("xxc_mail@163.com"); 
    		
    		
    		InternetAddress i2=new InternetAddress("winziyuan@gmail.com"); 
    		List<InternetAddress> l=new ArrayList<>();
    		l.add(i);
    		l.add(i2);
    		//MultiPartEmail email = new MultiPartEmail();
    		HtmlEmail email = new HtmlEmail();
    		 email.setHostName("smtp.163.com");
     		
     		email.setTo(l);
     		
     		email.setFrom("winziyuan@163.com", "Me");
     	    email.setAuthentication("winziyuan@163.com", "xiang@123"); 
     	   // email.setFrom("xiangxiancan@trends.com.cn", "Me");
     	  //  email.setAuthentication("xiangxiancan@trends.com.cn", "xiang@123"); 
     	    
     	    
     	    
     	   email.setCharset("UTF-8");
     		
    	/*	email.setSubject("一张图片");
    		email.setMsg("一张好图片！！！@ http://www.baidu.com/");

    		// add the attachment
    		email.attach(attachment);

    		// send the email
    		email.send();*/
     	   
     	// embed the image and get the content id
     	  URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
     	  String cid = email.embed(url, "Apache logo");

     	  // set the html message
     	  email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

     	  // set the alternative message
     	  email.setTextMsg("Your email client does not support HTML messages");

     	  // send the email
     	  email.attach(attachment);
     	  email.send();
     	   
     	   
     	   
     	   
     	   
     	   
     	   
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ok(re);
    }
   /* 
    @Test
    public void testmail(){
    	SimpleEmail email = new SimpleEmail();
    	try {
    		email.setHostName("mail.163.com");
    		email.addTo("winziyuan@163.com", "winziyuan");
    		email.setFrom("xiangxiancan@trends.com.cn", "Me");
    		email.setSubject("Test message");
    		email.setMsg("This is a simple test of commons-email");
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }*/
    
    
   // @Test
    public void test_msg(){
    	 HttpResponse httpResponse=null;
 		try {
 			HttpClient httpClient = new DefaultHttpClient(); 
 			HttpPost post=new HttpPost("http://utf8.sms.webchinese.cn");
 			
 			post.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码	
 			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
 			
 			formParams.add(new BasicNameValuePair("Uid", "winziyuan"));
 			formParams.add(new BasicNameValuePair("Key", "2740b7c1a7f81e6f773d"));
 			formParams.add(new BasicNameValuePair("smsMob","15811027039"));
 			formParams.add(new BasicNameValuePair("smsText","this code:1200【项】"));
 			
 			post.setEntity(new UrlEncodedFormEntity(formParams, HTTP.UTF_8));
 			httpResponse = httpClient.execute(post);
 			Header[]  headers = httpResponse.getAllHeaders();
 			int	statusCode = httpResponse.getStatusLine().getStatusCode();
 			System.out.println("statusCode:"+statusCode);
 			for(Header h : headers)
 			{
 				System.out.println(h.getName()+" "+h.getValue());
 			}
 			String result=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
 			
 			System.out.println("===result=====>"+result);
 			httpClient.getConnectionManager().shutdown();
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	    


    	
    }
    
    
    
    
    
    
    /**
     * Handles the form submission.
     */
   /* public static Result sayHello() {
   
        Form<Hello> form = form(Hello.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(index.render(form));
        } else {
            Hello data = form.get();
            return ok(
                hello.render(data.name, data.repeat, data.color)
            );
        }
    }*/
    
    public	static Result htmlt(){
    	
    	
    	return ok(views.html.htmlt.render());
    }
    
    
    
    //////// login
    public static Result login() {
    	
    	return ok(views.html.login.render());
    }
    //////// login
    public static Result index() {
    	
    	return ok(views.html.index.render());
    	
    }
    //////// login
    public static Result profile() {
    	
    	return ok(views.html.profile.render());
    }
    //////// login
    public static Result category() {
    	
    	return ok(views.html.category.render());
    }
    //////// login
    public static Result add_category() {
    	
    	return ok(views.html.add_category.render());
    }
    //////// login
    public static Result user() {
    	
    	return ok(views.html.user.render());
    }
    //////// login
    public static Result add_user() {
    	
    	return ok(views.html.add_user.render());
    }
    //////// login
    public static Result zaodian() {
    	
    	return ok(views.html.zaodian.render());
    }
    //////// login
    public static Result add_zaodian() {
    	
    	return ok(views.html.add_zaodian.render());
    }
    
    
    
    //////// login
    public static Result zaodian_save() {
    	//MultipartFormData form=request().body().asMultipartFormData();
    	//MultipartFormData form=request().body().asMultipartFormData();
    	Map<String, String[]>  form=request().body().asFormUrlEncoded();
    	Map<String,Object> form2=new HashMap<>();
    	for(Map.Entry<String, String[]> en : form.entrySet()){
    		//System.out.println(en.getKey()+"="+en.getValue()[0]);
    		form2.put(en.getKey(), en.getValue()[0]);
    	}
    	Map<String,Object> append=new HashMap<>();
    	append.put("sell_num", 0);
    	append.put("sell_pri", 0);
    	append.put("like_num", 0);
    	append.put("sub_num", 0);
    	append.put("order1_num", 0);
    	append.put("order0_num", 0);
    	append.put("state", 1);
    	append.putAll(form2);
    	map2save("test", "zaodian", append);
    	return ok();
    }
    //////// login
    public static Result login12() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login13() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login14() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login15() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login16() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login17() {
    	
    	return ok();
    }
    
    //////// login
    public static Result login18() {
    	
    	return ok();
    }
    
    
    public static String map2save(String database,String collection,Map<String, Object> map){
    	MongoClient client=null;
    	DB db2=null;
    	DBCollection coll=null;
    	try {
			client=getmongoclient();
			  db2 = client.getDB(database);
			 coll = db2.getCollection(collection);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	db2.requestStart();
    	BasicDBObject obj=new BasicDBObject(map).
				append("createtime", new Date());
    	System.out.println("obj save before===="+obj); 
    	try {
    		   coll.insert(obj);
    		   DBObject err = db2.getLastError();
    		   System.out.println(err);
    		} finally {
    			
    			db2.requestDone();
    			client.close();
    		}
    	return null;
    }
    
    
    
    
    
    
    
    
    
  
}
