package com.em.edumanager.bean;

/**
 * student bean
 */
public class StudentInfo implements java.io.Serializable {
    private int id;
    private String studentID;
    private String firstname;
    private String lastname;
    private String gender;
    private String age;
    private String major;
    private String remark;

	public StudentInfo(String stuID, String firName, String lastName, String age, String gender, String majorName, String remark) {
		this.studentID = stuID;
		this.firstname = firName;
		this.lastname = lastName;
		this.age = age;
		this.gender = gender;
		this.major = majorName;
		this.remark = remark;
	}

	public StudentInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
