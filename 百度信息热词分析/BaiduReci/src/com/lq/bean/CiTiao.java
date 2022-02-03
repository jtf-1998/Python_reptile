package com.lq.bean;

public class CiTiao {
	private int id;
	private String name;
	private String des;
	private String url;
	public CiTiao() {
		// TODO Auto-generated constructor stub
		super();
	}
	public CiTiao(String name,String des,String url) {
		this.name=name;
		this.des=des;
		this.url=url;
	}
	public CiTiao( int id,String name,String des,String url) {
		this.id=id;
		this.name=name;
		this.des=des;
		this.url=url;
	}
	
	@Override
	public String toString() {
		return "CiTiao [id=" + id + ", name=" + name + ", des=" + des + ", url=" + url + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
