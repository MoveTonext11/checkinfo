package com.anrongtec.lasa.entity;

/**
 * 主页面模块类， 用来存模块名字和图标的
 */
public class Function {
	int id;
	//名称
	String name;
	//图标
	int icon;

	public Function(int id, String name, int icon) {
		this.id = id;
		this.name = name;
		this.icon = icon;
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

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
