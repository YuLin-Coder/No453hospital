package com.hos.hospital.entity;

import java.util.Date;

public class User {
    private  String  name;
    private  int     phone;
    private  Date    shijiian;
    
    
	public User() {
		super();
	}
	public User(String name, int phone, Date shijiian) {
		super();
		this.name = name;
		this.phone = phone;
		this.shijiian = shijiian;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Date getShijiian() {
		return shijiian;
	}
	public void setShijiian(Date shijiian) {
		this.shijiian = shijiian;
	}
    
}
