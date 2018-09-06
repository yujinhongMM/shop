package com.yjh.practice.dao;

import java.util.ArrayList;

import com.yjh.practice.model.ProProSelStuView;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.ProjectSelect;
import com.yjh.practice.utils.PageUtils;

/**
 * 
 * Description 方案dao接口
 * @author YJH
 * @date 2018年6月4日  
 *
 */


public interface ProjectDao {

	/**
	 * 添加实训方案
	 * @param p 方案对象
	 * @return
	 */
	public boolean addProject(Project p);
	
	/**
	 * 修改方案，已通过审核方案不能修改
	 * @param p
	 * @return
	 */
	public boolean updateProject(Project p);
	
	/**
	 * 根据角色查询所有方案,分页
	 * @param role 角色    1-企业 9-管理员
	 * @param company_username 如果是企业查询，需传入company_username
	 * @param pageUtils 传入的该对象应设置pageNow和pageSize
	 * @return 
	 */
	public ArrayList<Project> findAllProject(int role,String company_username,PageUtils pageUtils);
	
	/**
	 * 删除方案，已通过审核无法删除
	 * @param p_no 方案号
	 * @return
	 */
	public boolean deleteProject(String p_no);
	
	/**
	 * 审核退审实训方案
	 * @param p_no
	 * @param  check true表示审核通过，false表示退审
	 * @return
	 */
	public boolean checkProject(String p_no,boolean check);
	
	/**
	 * 方案总结
	 * @param p_no 方案号
	 * @param content 总结内容
	 * @return
	 */
	public boolean summaryProject(String p_no,String content);
	
	/**
	 * 实训结束，结束部分
	 * @param p_nos
	 */
	public boolean endProjects(String p_nos[]);
	
	/**
	 * 学生查询所有正进行方案
	 * @param grade 年级
	 * @return
	 */
	public ArrayList<Project> findAllProject(int grade);
	
	/**
	 * 学生选择可选方案，不超过系统预设上限
	 * @param company_name  企业名称
	 * @param p_no  方案号
	 * @param stu_no  学生学号
	 * @param reason  选题理由
	 * @return
	 */
	public boolean chooseProject(String company_name,String p_no,String stu_no,String reason);
	
	/**
	 * 学生退选方案
	 * @param p_no
	 * @param stu_no  学生学号
	 * @return
	 */
	public boolean unChooseProject(String p_no,String stu_no);
	
	/**
	 * 企业查询学生选择本企业方案情况
	 * @param c_name 企业用户名
	 * @param pageUtils 分页工具类
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoice(String c_name,PageUtils pageUtils);
	
	/**
	 * 企业查询学生选择本企业某方案情况
	 * @param p_no 企业方案号
	 * @param pageUtils 分页工具类
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNo(String p_no,PageUtils pageUtils);
	
	/**
	 * 企业选择学生   学生已有确定方案不能被选择
	 * @param stu_no  学生学号
	 * @param p_no   方案号
	 * @return 
	 */
	public boolean chooseStudent(String stu_no,String p_no);
	
	/**
	 * 企业退选学生
	 * @param stu_nos 学生学号
	 * @param p_no  方案号
	 * @return 
	 */
	public boolean unChooseStudent(String stu_nos[],String p_no);
	
	/**
	 * 企业查询选题理由
	 * @param type 1-已确定 2-没有确定  3-按方案
	 * @param p_no 按方案查询时，该参数必填，按其他查询时填null
	 * @return 
	 */
	public ArrayList<ProjectSelect> findReason(int type,String p_no);
	
	/**
	 * 企业录入成绩，按方案号录入
	 * @param stu_nos 学生学号  与成绩一一对应
	 * @param scores 成绩
	 * @param p_no  方案号
	 * @return 
	 */
	public boolean inputScore(String stu_nos[],String scores[],String p_no);
	
	/**
	 * 按方案号查询学生成绩
	 * @param p_no
	 * @return
	 */
	public ArrayList<ProjectSelect> findScore(String p_no);
	
	/**
	 * 统计方案数量
	 * @return
	 */
	public int countProject();
	
	/**
	 * 统计单个企业的方案数量
	 * @return
	 */
	public int countCompanyProject(String company_username);
	
	/**
	 * 通过no查询方案信息
	 * @param no
	 * @return
	 */
	public Project findProjectByNo(String no);
	
	/**
	 * 得到某年的方案号最大值
	 * @return
	 */
	public int findMaxProjectNo(int year);
	
	/**
	 * 查询所有正进行方案
	 * @return
	 */
	public ArrayList<Project> findAllStartedProject();
	
	/**
	 * 查询学生已选方案
	 * @param stu_no
	 * @return
	 */
	public ArrayList<Project> findAllChosenProject(String stu_no);
	
	/**
	 * 根据企业用户名，统计学生选择的数量
	 * @param c_name
	 * @return
	 */
	public int countAllStudentChoice(String c_name);
	
	/**
	 * 根据企业方案号，统计学生选择的数量
	 * @param p_no
	 * @param type: 1-已被企业选择  2-未被企业选择  3-全部
	 * @return
	 */
	public int countAllStudentChoiceByPNoAndType(String p_no,String type);
	
	
	/**
	 * 根据角色查询所有方案,分页
	 * @param role 角色    1-企业 9-管理员
	 * @param company_username 如果是企业查询，需传入company_username
	 * @param pageUtils 传入的该对象应设置pageNow和pageSize
	 * @param checkState 审核状态
	 * @param year  方案生成年份
	 * @return 
	 */
	public ArrayList<Project> findAllProject(int role,String company_username,PageUtils pageUtils,boolean checkState,String year);
	
	/**
	 * 统计某年已审核/未审核方案数数量，管理员查询时，company_username传入空值
	 * @param year 方案发布年
	 * @param checkState 方案审核状态
	 * @return
	 */
	public int countProject(String year,boolean checkState,String company_username);
	
	/**
	 * 查询企业所有方案
	 * @param company_username 
	 * @return 
	 */
	public ArrayList<Project> findAllProject(String company_username);
	
	/**
	 * 查询学生成绩，分页工具类pageUtils为空时表示非分页查询
	 * @param p_no
	 * @param pageUtils
	 * @return
	 */
	public ArrayList<ProProSelStuView> findStuScoreByPNo(String p_no, PageUtils pageUtils);
	
	/**
	 * 通过方案号统计已选该方案且被企业选择的学生的数量
	 * @param no
	 * @return
	 */
	public int countAllSelStuByNo(String no);
	
	/**
	 * 查找学生已确定的实训方案
	 * @param stu_no
	 * @return
	 */
	public ArrayList<ProjectSelect> findStuProject(String stu_no);
	
	/**
	 * 企业查询学生选择本企业某方案情况
	 * @param p_no 企业方案号
	 * @param type 1-已选择、2-未选择学生
	 * @param pageUtils 分页工具类
	 * @return 
	 */
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNoAndType(String p_no,String type,PageUtils pageUtils);

	/**
	 * 得到所有专业
	 * @return
	 */
	public ArrayList<String> findAllProfessional();
}

