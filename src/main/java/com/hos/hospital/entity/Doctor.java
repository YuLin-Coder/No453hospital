package com.hos.hospital.entity;

import java.util.Date;

public class Doctor {
    private Integer id;

    private String username;

    private String passwoed;

    private String name;

    private String forte;

    private String position;

    private String hosname;

    private String introduce;

    private Integer numbers;

    private Integer evaluate;

    private String img;

    private Integer sid;

    private String sname;

    private Date begindate;

    private Date enddate;

    private Date begintime;

    private Date endtime;

    private Integer peoples;

    private Integer yipeoples;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed == null ? null : passwoed.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getForte() {
        return forte;
    }

    public void setForte(String forte) {
        this.forte = forte == null ? null : forte.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getHosname() {
        return hosname;
    }

    public void setHosname(String hosname) {
        this.hosname = hosname == null ? null : hosname.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    public Integer getYipeoples() {
        return yipeoples;
    }

    public void setYipeoples(Integer yipeoples) {
        this.yipeoples = yipeoples;
    }
}