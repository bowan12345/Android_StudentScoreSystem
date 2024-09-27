package com.em.edumanager.bean;

import java.io.Serializable;

/**
 * score bean
 */
public class StudentGrade implements Serializable {
    private int id;
    private String studentId;
    private String firstname;
    private String lastname;
    private String android;
    private String java;
    private String html;

	public StudentGrade(String stuId, String firstName, String lastName, String android, String java, String html) {
		this.studentId = stuId;
		this.firstname = firstName;
		this.lastname = lastName;
		this.android = android;
		this.java = java;
		this.html = html;
	}

	public StudentGrade() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAndroid() {
		return android;
	}
	public void setAndroid(String android) {
		this.android = android;
	}
	public String getJava() {
		return java;
	}
	public void setJava(String java) {
		this.java = java;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
