package com.oracle.gdms.entity;

public class GoodsTypeBean  {
	private int gtid;
	private String name;
	private String description;
	public GoodsTypeBean() {}
	public GoodsTypeBean(int gtid, String name, String description) {
		this.gtid = gtid;
		this.name = name;
		this.description = description;
	}
	public int getGtid() {
		return gtid;
	}
	public void setGtid(int gtid) {
		this.gtid = gtid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
