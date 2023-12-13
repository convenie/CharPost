package model;

public class PostBean {

	private String name;
	private String chara;
	private String charcolor;
	private String charfont;
	private String time;
	private int like;
	private int dislike;
	public PostBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public PostBean(String name,String chara,String charcolor,String charfont,String time,int like,int dislike) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.setName(name);
		this.setChara(chara);
		this.setCharcolor(charcolor);	
		this.setCharfont(charfont);
		this.setTime(time);
		this.setLike(like);
		this.setDislike(dislike);
	}
	public String getName() {return name;}
	public void setName(String name) {this.name=name;}
	public String getChara() {return chara;}
	public void setChara(String chara) {this.chara=chara;}
	public String getCharcolor() {return charcolor;}
	public void setCharcolor(String charcolor) {this.charcolor=charcolor;}
	public String getCharfont() {return charfont;}
	public void setCharfont(String charfont) {this.charfont=charfont;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time=time;}
	public int getLike() {return like;}
	public void setLike(int like) {this.like=like;}
	public int getDislike() {return dislike;}
	public void setDislike(int dislike) {this.dislike=dislike;}

}
