package com.yakyak.UserStory2.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "labyak")
public class LabYak {

	private String name;
	private float age;
	private String sex;
	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public float getAge() {
		return age;
	}
	@XmlElement
	public void setAge(float age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	@XmlElement
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
