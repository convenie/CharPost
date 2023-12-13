package model;

public class CustomerBean {
	
	public int ID;
	private String name;
	private String password;
	public CustomerBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public CustomerBean(int ID,String name,String password) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.setID(ID);
		this.setName(name);
		this.setPassword(password);
		
	}
	public int getID() {return ID;}
	public void setID(int id) {this.ID=id;}
	public String getName() {return name;}
	public void setName(String name) {this.name=name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password=password;}

}
