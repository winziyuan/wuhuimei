package model;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.mongodb.DBObject;

public class user implements DBObject{
	ObjectId id;
	String name;
	long age;

	
	
	
	public ObjectId getId() {
		return id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean containsField(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("1");
		System.out.println(arg0);
		return false;
	}

	@Override
	@Deprecated
	public boolean containsKey(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("2");
		return false;
	}

	@Override
	public Object get(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("3");
		System.out.println(arg0);
		return null;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		System.out.println("4");
		return null;
	}

	@Override
	public Object put(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("5");
		System.out.println(arg0);
		System.out.println(arg1);
		return null;
	}

	@Override
	public void putAll(BSONObject arg0) {
		// TODO Auto-generated method stub
		System.out.println("6");
	}

	@Override
	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		System.out.println("7");
	}

	@Override
	public Object removeField(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("8");
		return null;
	}

	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		System.out.println("9");
		return null;
	}

	@Override
	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		System.out.println("10");
		return false;
	}

	@Override
	public void markAsPartialObject() {
		// TODO Auto-generated method stub
		System.out.println("11");
	}

}
