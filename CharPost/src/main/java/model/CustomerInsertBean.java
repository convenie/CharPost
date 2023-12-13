package model;

import servlet.IDLogic;

public class CustomerInsertBean {
	
	public String ID;
	private String name;
	private String password;
	public int count=2023000000;
	public CustomerInsertBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public CustomerInsertBean(String ID,String name,String password,int count) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.setID(Integer.toString(count+1));
		this.setName(name);
		this.setPassword(password);
		
	}
	public String getID() {return ID;}
	public void setID(String string) {this.ID=string;}
	public String getName() {return name;}
	public void setName(String name) {this.name=name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password=password;}
	public int getCount() {
		count=IDLogic.getIDplus()+1;
		//count=2023000010;
		return count;
		}
	public void setCount(int count) {
		this.count=count+1;
		}
}
