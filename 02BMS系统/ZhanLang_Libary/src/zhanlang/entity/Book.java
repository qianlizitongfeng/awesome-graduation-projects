package zhanlang.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Book implements Serializable{
	/**图书编号*/
	private String ID;
	/**图书名称*/
	private String bName;
	/**图书作者*/
	private String author;
	/**图书封面*/
	private String image="image";
	/**图书出版社*/
	private String press;
	/**借阅者   */
	private String borrow="borrow";//==========================
	/**入库时间*/
	private String inTime="inTime";
	/**借出时间*/
	private String outTime="outTime";
	/**图书类型*/
	private String type;//======================
	/**简介*/
	private String introduce="introduce";
	
	/**
	 * Book  有参  与  无参   构造方法
	 */
	public Book() {
		super();
	}

	public Book(String id){
		this.ID = id;
	}
	public Book(String bName, String author,String press,String inTime){
		this.bName = bName;
		this.author = author;
		this.press = press;
		this.inTime = inTime;
	}
	public Book(String bName, String author,String type,String press,String inTime){
		this.bName = bName;
		this.author = author;
		this.type=type;
		this.press = press;
		this.inTime = inTime;
	}
	public Book(String image,String bName, String author,String type,String press,String inTime){
		this.image=image;
		this.bName = bName;
		this.author = author;
		this.type=type;
		this.press = press;
		this.inTime = inTime;
	}
	public Book(String image,String bName, String author,String type,String press,String inTime,String introduce){
		this.image=image;
		this.bName = bName;
		this.author = author;
		this.type=type;
		this.press = press;
		this.inTime = inTime;
		this.introduce=introduce;
	}
	public Book(String id,String image,String bName, String author,  String press, String borrow, String inTime,
			String outTime,String type,String introduce) {
		super();
		this.ID = id;
		this.bName = bName;
		this.author = author;
		this.image = image;
		this.press = press;
		this.borrow = borrow;
		this.inTime = inTime;
		this.outTime = outTime;
		this.type = type;
		this.introduce=introduce;
	}
	public Book(String iD, String borrow) {
		super();
		ID = iD;
		this.borrow = borrow;
	}
	
	public Book(String iD, String bName, String type) {
		super();
		ID = iD;
		this.bName = bName;
		this.type = type;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getBorrow() {
		return borrow;
	}
	public void setBorrow(String borrow) {
		this.borrow = borrow;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	@Override
	public String toString() {
		return  ID + "\t"+ image+"\t" + bName + "\t" + author + "\t" + press + "\t" + borrow + "\t" + inTime + "\t" + outTime + "\t"
				+ type;
	}
	
	
}