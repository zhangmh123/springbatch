package com.neusoft.springbatch.sample.cvs;


/** Pojo��_Student */
public class Student {
    /** ID */
    private String ID = "";
    /** ���� */
    private String name = "";
    /** ���� */
    private int age = 0;
    /** ���� */
    private float score = 0;
    /*getter ��setter��ɾ��*/
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
    
    
}