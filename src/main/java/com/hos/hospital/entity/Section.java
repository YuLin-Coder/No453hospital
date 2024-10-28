package com.hos.hospital.entity;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class Section {
    private Integer id;

    private String name;

    private String img;

    private Integer pid;

    private Integer type;
    
    private List<Section> slist;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public List<Section> getSlist() {
		return slist;
	}

	public void setSlist(List<Section> slist) {
		this.slist = slist;
	}



}