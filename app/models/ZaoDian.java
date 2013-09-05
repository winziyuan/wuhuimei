package models;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.mongodb.DBObject;

public class ZaoDian implements DBObject{
	ObjectId id;
	String name;
	String icon;
	int sell_pri;
	int sell_num;
	int price;
	int order1_num;
	int order0_num;
	int state;
	int like_num;
	int sub_num;
	String note;
	String createtime;

	
	
	
	
	
	
	
	
	
	
	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}



	public int getSell_pri() {
		return sell_pri;
	}



	public void setSell_pri(int sell_pri) {
		this.sell_pri = sell_pri;
	}



	public int getSell_num() {
		return sell_num;
	}



	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getOrder1_num() {
		return order1_num;
	}



	public void setOrder1_num(int order1_num) {
		this.order1_num = order1_num;
	}



	public int getOrder0_num() {
		return order0_num;
	}



	public void setOrder0_num(int order0_num) {
		this.order0_num = order0_num;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public int getLike_num() {
		return like_num;
	}



	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}



	public int getSub_num() {
		return sub_num;
	}



	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getCreatetime() {
		return createtime;
	}



	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}







	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public ZaoDian() {
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
		//System.out.println("==================");
		if (arg0.endsWith("_id")) {
			setId((ObjectId)arg1); 
		}
		if (arg0.endsWith("icon")) {
			setIcon((String)arg1);
		}
		if (arg0.endsWith("sell_pri")) {
			setSell_pri((int)arg1);
		}
		if (arg0.endsWith("sell_num")) {
			setSell_num((int)arg1);
		}
		if (arg0.endsWith("price")) {
			setPrice(Integer.valueOf((String)arg1));
		}
		if (arg0.endsWith("order1_num")) {
			setOrder1_num((int)arg1);
		}
		if (arg0.endsWith("order0_num")) {
			setOrder0_num((int)arg1);
		}
		if (arg0.endsWith("name")) {
			setName((String)arg1);
		}
		if (arg0.endsWith("state")) {
			setState((int)arg1);
		}
		if (arg0.endsWith("like_num")) {
			setLike_num((int)arg1);
		}
		if (arg0.endsWith("note")) {
			setNote((String)arg1);
		}
		if (arg0.endsWith("sub_num")) {
			setSub_num((int)arg1);
		}
		if (arg0.endsWith("createtime")) {
			
			setCreatetime(((Date)arg1).toLocaleString());
		}
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
