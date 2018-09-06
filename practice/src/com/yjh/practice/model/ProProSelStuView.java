package com.yjh.practice.model;

/**
 * 
 * Description 方案与学生的实体联系
 * @author YJH
 * @date 2018年6月4日  
 *
 */

public class ProProSelStuView {
	private Project project;
	private ProjectSelect projectSelect;
	private Student student;
	
	public ProProSelStuView(){
		project=new Project();
		projectSelect=new ProjectSelect();
		student=new Student();
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ProjectSelect getProjectSelect() {
		return projectSelect;
	}
	public void setProjectSelect(ProjectSelect projectSelect) {
		this.projectSelect = projectSelect;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}

