package com.yjh.practice.service;

import java.util.ArrayList;
/**
 * 
 * Description
 * @author YJH
 * @date 2018年6月4日  
 *
 */
public interface ProjectService {
	/**
	 * 生成project的No,年度+序号
	 * @return
	 */
	public String getProjectNo();
	/**
	 * 传入年级，返回Grade
	 * @param n 1,2,3,4
	 * @return
	 */
	public int getStuGrade(int n);
	
	/**
	 * 判断方案是否属于用户
	 * @param username
	 * @param p_no
	 * @return
	 */
	public boolean findProjectBelongToUserByPNo(String username,String p_no);
	
	/**
	 * 在选择实训开始时间之后在选择实训结束时间之前返回true
	 * @return
	 */
	public boolean findPracticeIsUnderWay();
	
	/**
	 * 在添加实训开始时间之后在添加实训结束时间之前返回true
	 * @return
	 */
	public boolean findAddPracticeIsUnderWay();
	
	/**
	 * 返回从系统开始到现在的年份的数组
	 * @return
	 */
	public int[] findAllAddProjectYear();
	
	/**
	 * 得到所有专业
	 * @return
	 */
	public ArrayList<String> findAllProfessional();
}

