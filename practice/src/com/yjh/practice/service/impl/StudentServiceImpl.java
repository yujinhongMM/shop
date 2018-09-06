package com.yjh.practice.service.impl;

import java.util.List;

import com.yjh.practice.dao.StudentDao;
import com.yjh.practice.dao.impl.StudentDaoImpl;
import com.yjh.practice.model.Student;
import com.yjh.practice.service.StudentService;

/**
 * 
 * Description 学生信息管理操作实现
 * @author YJH
 * @date 2018年6月2日  
 *
 */

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();
	
	/**
	 * 插入学生记录
	 * @param: student 一个学生实体
	 * @return: true 插入成功；false 插入失败
	 */
	@Override
	public boolean insert(Student student) {
		try {			
			if (student != null ) 
				return this.studentDao.insert(student);
			} catch(Exception exception) {
				return false;
			}
		return false;
	}

	/**
	 * 查询所有学生记录
	 * @return: 所有学生实体列表
	 */
	@Override
	public List<Student> findAll() {
		try {
			return this.studentDao.findAll();
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * 查询指定学号学生记录
	 * @param id 学号
	 * @return 查到学生实体
	 */
	@Override
	public Student findById(String id) {
		if(id!=null){
			try {
				return this.studentDao.findById(id);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;
	}

	/**
	 * 查询已选/未选学生（涉及多表查询，学生表+方案选择表）
	 * @param flag：1，查询已选学生；2，查询未选学生
	 * @return 查到学生实体列表 
	 */
	@Override
	public List<Student> findBySelected(int flag) {
		try {
			return this.studentDao.findBySelected(flag);
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * 管理员根据企业名称查询学生信息
	 * @param companyName 企业名称
	 * @return 查找到学生实体列表
	 */
	@Override
	public List<Student> findByCompany(String companyName) {
		if(companyName!=null){
			try {
				return this.studentDao.findByCompany(companyName);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;		
	}

	/**
	 * 查询指定年级的学生记录
	 * @param grade 年级
	 * @return 查到学生实体列表
	 */
	@Override
	public List<Student> findByGrade(int grade) {
		try {
			return this.studentDao.findByGrade(grade);
		} catch(Exception e) {
			return null ;
		}		
	}

	/**
	 * 查询指定专业的学生
	 * @param major 专业名称
	 * @return 查到学生实体列表
	 */
	@Override
	public List<Student> findByMajor(String major) {
		if(major!=null){
			try {
				return this.studentDao.findByMajor(major);
			} catch(Exception e) {
				return null ;
			}
		}
		return null;	
	}

	/**
	 * 管理员按年度查询学生记录（方案号中隐含年度）
	 * @param year 年度
	 * @return 查到学生实体列表
	 */
	@Override
	public List<Student> findByYear(int year) {
		try {
			return this.studentDao.findByYear(year);
		} catch(Exception e) {
			return null ;
		}
	}



	/**
	 * 更新学生记录
	 * @param student 一个学生实体
	 * @return true:成功；false：失败 
	 */
	@Override
	public boolean update(Student student) {
		if(student!=null){
			try {
				return this.studentDao.update(student);
			} catch(Exception e) {
				return false ;
			}
		}
		return false;
	}

	/**
	 * 删除指定学生
     * @param id 学号
     * @return true 删除成功；false 删除失败
	 */
	@Override
	public boolean delete(String id) {
		if(id!=null){
			try {
				return this.studentDao.delete(id);
			} catch(Exception e) {
				return false ;
			}
		}
		return false;
	}

	

}
