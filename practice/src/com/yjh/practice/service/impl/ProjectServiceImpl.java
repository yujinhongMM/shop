package com.yjh.practice.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.dao.impl.SystemParameterDaoImpl;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.SystemParameter;
import com.yjh.practice.service.ProjectService;

/**
 * 
 * Description
 * @author YJH
 * @date 2018Äê6ÔÂ4ÈÕ  
 *
 */

public class ProjectServiceImpl implements ProjectService {

	@Override
	public String getProjectNo() {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		int m = projectDaoImpl.findMaxProjectNo(Calendar.getInstance().get(Calendar.YEAR));
		if (m > 0) {
			return m + 1 + "";
		} else if (m == 0) {
			return Calendar.getInstance().get(Calendar.YEAR) + "000001";
		} else {
			return null;
		}
	}

	@Override
	public int getStuGrade(int n) {
		Calendar date = Calendar.getInstance();
		if (date.get(Calendar.MONTH) > 8) {
			return date.get(Calendar.YEAR) - n + 1;
		} else
			return date.get(Calendar.YEAR) - n;
	}

	@Override
	public boolean findProjectBelongToUserByPNo(String username, String p_no) {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		Project project = projectDaoImpl.findProjectByNo(p_no);
		if (project.getCompanyUsername().equals(username))
			return true;
		return false;
	}

	@Override
	public boolean findPracticeIsUnderWay() {
		SystemParameterDaoImpl systemParameterDaoImpl = new SystemParameterDaoImpl();
		SystemParameter systemParameter = systemParameterDaoImpl.queryByAccount("admin");
		Date data = Calendar.getInstance().getTime();
		if (systemParameter == null) {
			return false;
		} else if (systemParameter.getStudentSelStartDate().before(data)
				&& systemParameter.getStudentSelEndDate().after(data)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean findAddPracticeIsUnderWay() {
		SystemParameterDaoImpl systemParameterDaoImpl = new SystemParameterDaoImpl();
		SystemParameter systemParameter = systemParameterDaoImpl.queryByAccount("admin");
		Date data = Calendar.getInstance().getTime();
		if (systemParameter == null) {
			return false;
		} else if (systemParameter.getReleaseProjectStartDate().before(data)
				&& systemParameter.getReleaseProjectEndDate().after(data)) {
			return true;
		}
		return false;
	}

	@Override
	public int[] findAllAddProjectYear() {
		int PRACTICE_SYSTEM_START_YEAR = 2017;
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		int len = nowYear - PRACTICE_SYSTEM_START_YEAR + 1;
		int years[] = new int[len];
		for (int i = 0; i < len; i++) {
			years[i] = PRACTICE_SYSTEM_START_YEAR + i;
		}
		return years;
	}

	@Override
	public ArrayList<String> findAllProfessional() {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		ArrayList<String> professionals = projectDaoImpl.findAllProfessional();
		return professionals;
	}


}
