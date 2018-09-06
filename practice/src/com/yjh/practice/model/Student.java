package com.yjh.practice.model;

/**
 * 
 * Description 学生
 * @author YJH
 * @date 2018年6月2日  
 *
 */
public class Student implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String no;
	private String name;
	private Integer grade;
	private String level;
	private String professional;
	private String gender;
	private String class_;
	private String password;
	private String mailbox;
	private String subjectBackground;
	private String learningExperience;
	private String researchDirection;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String no, String name, Integer grade, String level,
			String professional, String class_) {
		this.no = no;
		this.name = name;
		this.grade = grade;
		this.level = level;
		this.professional = professional;
		this.class_ = class_;
	}

	/** full constructor */
	public Student(String no, String name, Integer grade, String level,
			String professional, String gender, String class_, String password,
			String mailbox, String subjectBackground,
			String learningExperience, String researchDirection) {
		this.no = no;
		this.name = name;
		this.grade = grade;
		this.level = level;
		this.professional = professional;
		this.gender = gender;
		this.class_ = class_;
		this.password = password;
		this.mailbox = mailbox;
		this.subjectBackground = subjectBackground;
		this.learningExperience = learningExperience;
		this.researchDirection = researchDirection;
	}

	// Property accessors

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailbox() {
		return this.mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getSubjectBackground() {
		return this.subjectBackground;
	}

	public void setSubjectBackground(String subjectBackground) {
		this.subjectBackground = subjectBackground;
	}

	public String getLearningExperience() {
		return this.learningExperience;
	}

	public void setLearningExperience(String learningExperience) {
		this.learningExperience = learningExperience;
	}

	public String getResearchDirection() {
		return this.researchDirection;
	}

	public void setResearchDirection(String researchDirection) {
		this.researchDirection = researchDirection;
	}

}