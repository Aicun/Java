package com.lac.po;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private int id;
	private String name;
	private String gender;
	private int age;
	private ClassRoom classRoom;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ClassRoom getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	
	
	public Student(int id, String name, int age, ClassRoom classRoom) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.age = age;  
        this.classRoom = classRoom;  
    }
	
	public Student(){
		super();
	}
}
