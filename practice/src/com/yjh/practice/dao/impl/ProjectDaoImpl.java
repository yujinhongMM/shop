package com.yjh.practice.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.yjh.practice.dao.ProjectDao;
import com.yjh.practice.model.ProProSelStuView;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.ProjectSelect;
import com.yjh.practice.model.ProjectSelectId;
import com.yjh.practice.service.impl.ProjectServiceImpl;
import com.yjh.practice.utils.DbUtils;
import com.yjh.practice.utils.PageUtils;




public class ProjectDaoImpl implements ProjectDao {
	
	@Override
	public boolean addProject(Project p) {
		String sql = "INSERT INTO project(no,name,introduction,students_num,company_username,"
				+ "release_date,grade,category,major,company_teacher,company_teacher_title) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		// 通过projectServiceImpl得到no
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		String no = projectServiceImpl.getProjectNo();
		if (no == null) {
			return false;
		}
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(1, no);
			ps.setString(2, p.getName());
			ps.setString(3, p.getIntroduction());
			ps.setInt(4, p.getStudentsNum());
			ps.setString(5, p.getCompanyUsername());
			ps.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
			ps.setInt(7, p.getGrade());
			ps.setString(8, p.getCategory());
			ps.setString(9, p.getMajor());

			// 前面为必需属性，后面为可选
			ps.setString(10, p.getCompanyTeacher());
			ps.setString(11, p.getCompanyTeacherTitle());
			ps.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public boolean updateProject(Project p) {
		// 该sql语句用于查询传入方案是否已审核，审核后不能修改
		String sql1 = "SELECT audit_date FROM project WHERE No=?";
		// 修改方案属性sql语句   company_username=?,
		String sql2 = "UPDATE project SET name=?,introduction=?,students_num=?,"
				+ "grade=?,category=?,major=?,company_teacher=?,company_teacher_title=? " + "WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql1);
			ps.setString(1, p.getNo());
			rs = ps.executeQuery();
			Date date = null;
			if (rs.next())
				date = rs.getDate("audit_date");
			// 审核时间不为空表示已审核，不能修改
			if (date != null)
				return false;
			else {
				ps.close();
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql2);
				ps.setString(1, p.getName());
				ps.setString(2, p.getIntroduction());
				ps.setInt(3, p.getStudentsNum());
				//ps.setString(4, p.getCompanyUsername());
				ps.setInt(4, p.getGrade());
				ps.setString(5, p.getCategory());
				ps.setString(6, p.getMajor());
				ps.setString(7, p.getCompanyTeacher());
				ps.setString(8, p.getCompanyTeacherTitle());
				ps.setString(9, p.getNo());
				ps.executeUpdate();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public ArrayList<Project> findAllProject(int role, String company_username, PageUtils pageUtils) {
		String sql = null;
		int totalSize = 0;
		if (role == 9) {
			sql = "SELECT * FROM project LIMIT ?,?";
			totalSize = countProject();
			pageUtils.setTotalSize(totalSize);
		} else if (role == 1 && company_username != null) {
			sql = "SELECT * FROM project WHERE company_username=? LIMIT ?,?";
			totalSize = countCompanyProject(company_username);
			pageUtils.setTotalSize(totalSize);
		} else
			return null;
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			if (role == 9) {
				ps.setInt(1, start);
				ps.setInt(2, size);
			} else {
				ps.setString(1, company_username);
				ps.setInt(2, start);
				ps.setInt(3, size);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public boolean deleteProject(String p_no) {
		// 该sql语句用于查询传入方案是否已审核，审核后不能修改
		String sql1 = "SELECT audit_date FROM project WHERE No=?";
		String sql2 = "DELETE FROM project WHERE No=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql1);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			Date date = null;
			if (rs.next())
				date = rs.getDate("audit_date");
			// 审核时间不为空表示已审核，不能修改
			if (date != null)
				return false;
			else {
				ps.close();
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql2);
				ps.setString(1, p_no);
				ps.execute();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public boolean checkProject(String p_no, boolean check) {
		String sql = "UPDATE project SET audit_date=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(2, p_no);
			if (!check) {
				ps.setString(1, null);
			} else {
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				ps.setDate(1, date);
			}
			ps.executeUpdate();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public boolean summaryProject(String p_no, String content) {
		String sql = "UPDATE project SET summary=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(1, content);
			ps.setString(2, p_no);
			ps.executeUpdate();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public boolean endProjects(String[] p_nos) {
		String sql = "UPDATE project SET end_date=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < p_nos.length; i++) {
				ps.setDate(1, date);
				ps.setString(2, p_nos[i]);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public ArrayList<Project> findAllProject(int grade) {
		String sql = "SELECT * FROM project WHERE grade>=? and end_date is NULL and audit_date is not NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, grade);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public boolean chooseProject(String company_name, String p_no, String stu_no, String reason) {
		// 查询单个学生已选方案数的sql语句
		String sql1 = "SELECT COUNT(*) m FROM project_select WHERE studentNo=? AND score IS NULL";
		// 查询系统预设学生可选方案数上限的sql语句
		String sql2 = "SELECT student_sel_maxnum m FROM system_parameter";
		// 增加学生选择方案的sql语句
		String sql3 = "INSERT INTO project_select(studentNo,projectNo,sel_reason,company_name) VALUES(?,?,?,?)";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询学生已选方案数
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql1);
			ps.setString(1, stu_no);
			rs = ps.executeQuery();
			int stu_sel_max = 0;
			if (rs.next())
				stu_sel_max = rs.getInt("m");
			ps.close();
			rs.close();
			// 查询系统预设单个学生可选方案数上限
			ps = connection.prepareStatement(sql2);
			rs = ps.executeQuery();
			int sys_sel_max = 0;
			if (rs.next())
				sys_sel_max = rs.getInt("m");
			ps.close();
			rs.close();
			// 如果学生已选方案总数小于系统设置的上限，进行添加操作
			if (stu_sel_max < sys_sel_max) {
				ps = connection.prepareStatement(sql3);
				ps.setString(1, stu_no);
				ps.setString(2, p_no);
				ps.setString(3, reason);
				ps.setString(4, company_name);
				ps.execute();
				connection.commit();
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public boolean unChooseProject(String p_no, String stu_no) {
		String sql = "DELETE FROM project_select WHERE studentNo=? and projectNo=? and company_sel_date IS NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(1, stu_no);
			ps.setString(2, p_no);
			ps.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public ArrayList<ProProSelStuView> findAllStudentChoice(String c_name, PageUtils pageUtils) {
		String sql="SELECT * FROM project_select,project,student WHERE student.No=project_select.studentNo AND project.No=project_select.projectNo And project_select.company_name=? LIMIT ?,?";
		int totalSize = countAllStudentChoice(c_name);
		System.out.println(c_name);
		if (totalSize < 0)
			return null;
		pageUtils.setTotalSize(totalSize);
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProProSelStuView> proProSelStuViews = new ArrayList<>();
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		try {
			System.out.println(sql);
			ps = connection.prepareStatement(sql);
			ps.setString(1, c_name);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs = ps.executeQuery();
			int tiaoshu=rs.getRow();
			System.out.println(tiaoshu);
			//System.out.println("rs.next()="+rs.next());
			
			//if(tiaoshu>0){
			while (rs.next()) {
				ProProSelStuView proProSelStuView = new ProProSelStuView();
				
				// projectSelect属性设置
				proProSelStuView.getProjectSelect().setCompanyName(rs.getString("company_name"));
				proProSelStuView.getProjectSelect().setCompanySelDate(rs.getDate("company_sel_date"));
				proProSelStuView.getProjectSelect().setId(new ProjectSelectId());
				proProSelStuView.getProjectSelect().getId().setProjectNo(Integer.parseInt(rs.getString("projectNo")));
				proProSelStuView.getProjectSelect().getId().setStudentNo(rs.getString("studentNo"));
				proProSelStuView.getProjectSelect().setScore(rs.getString("score"));
				proProSelStuView.getProjectSelect().setSelReason(rs.getString("sel_reason"));

				// project属性设置
				proProSelStuView.getProject().setAuditDate(rs.getDate("audit_date"));
				proProSelStuView.getProject().setCategory(rs.getString("category"));
				proProSelStuView.getProject().setCompanyTeacher(rs.getString("company_teacher"));
				proProSelStuView.getProject().setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				proProSelStuView.getProject().setCompanyUsername(rs.getString("company_name"));
				proProSelStuView.getProject().setEndDate(rs.getDate("end_date"));
				proProSelStuView.getProject().setGrade(rs.getInt("grade"));
				proProSelStuView.getProject().setIntroduction(rs.getString("introduction"));
				proProSelStuView.getProject().setMajor(rs.getString("major"));
				proProSelStuView.getProject().setName(rs.getString("name"));
				proProSelStuView.getProject().setNo(rs.getString("No"));
				proProSelStuView.getProject().setReleaseDate(rs.getDate("release_date"));
				proProSelStuView.getProject().setStudentsNum(rs.getInt("students_num"));
				proProSelStuView.getProject().setSummary(rs.getString("summary"));
				proProSelStuViews.add(proProSelStuView);
				
				// student属性设置
				proProSelStuView.getStudent().setClass_(rs.getString("class"));
				proProSelStuView.getStudent().setGender(rs.getString("gender"));
				proProSelStuView.getStudent().setGrade(rs.getInt("student.grade"));
				proProSelStuView.getStudent().setLearningExperience(rs.getString("learning_experience"));
				proProSelStuView.getStudent().setLevel(rs.getString("level"));
				proProSelStuView.getStudent().setMailbox(rs.getString("mailbox"));
				proProSelStuView.getStudent().setName(rs.getString("student.name"));
				proProSelStuView.getStudent().setNo(rs.getString("student.No"));
				proProSelStuView.getStudent().setProfessional(rs.getString("professional"));
				proProSelStuView.getStudent().setResearchDirection(rs.getString("research_direction"));
				proProSelStuView.getStudent().setSubjectBackground(rs.getString("subject_background"));

				System.out.println(proProSelStuView.getStudent().getNo().toString());
			}
			//}
			
			return proProSelStuViews;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		
		}
		
		return proProSelStuViews;
	}

	@Override
	public boolean chooseStudent(String stu_no, String p_no) {
		// 查看当前学生是否已有确定方案的sql语句
		String sql1 = "SELECT company_sel_date FROM project_select WHERE studentNo=? AND company_sel_date IS NOT NULL AND score IS NULL";
		// 选择学生的sql语句
		String sql2 = "UPDATE project_select SET company_sel_date=?  WHERE studentNo=? AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询学生已确定方案
			ps = connection.prepareStatement(sql1);
			ps.setString(1, stu_no);
			rs = ps.executeQuery();
			if (!rs.next()) {
				// 没有已确定方案
				ps.close();
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql2);
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				ps.setDate(1, date);
				ps.setString(2, stu_no);
				ps.setString(3, p_no);
				ps.executeUpdate();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public boolean unChooseStudent(String[] stu_nos, String p_no) {
		String sql = "UPDATE project_select SET company_sel_date=NULL  WHERE studentNo=? AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(2, p_no);
			if (stu_nos.length == 1) {
				ps.setString(1, stu_nos[0]);
				ps.execute();
				System.out.println("退选一个学生");
			} else {
				for (int i = 0; i < stu_nos.length; i++) {
					ps.setString(1, stu_nos[i]);
					ps.addBatch();
				}
				ps.executeBatch();
			}
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public ArrayList<ProjectSelect> findReason(int type, String p_no) {
		String sql = null;
		if (type == 1) {
			sql = "SELECT * FROM project_select WHERE company_sel_date IS NOT NULL";
		} else if (type == 2) {
			sql = "SELECT * FROM project_select WHERE company_sel_date IS NULL";
		} else if (type == 3) {
			sql = "SELECT * FROM project_select WHERE projectNo=?";
		} else
			return null; // 参数传入错误
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProjectSelect> selects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			if (type == 3)
				ps.setString(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProjectSelect projectSelect = new ProjectSelect();
				projectSelect.setCompanyName(rs.getString("company_name"));
				projectSelect.setCompanySelDate(rs.getDate(""));
				projectSelect.setId(new ProjectSelectId(rs.getString("studentNo"), rs.getInt("projectNo")));
				projectSelect.setScore(rs.getString("score"));
				projectSelect.setSelReason(rs.getString("sel_reason"));
				selects.add(projectSelect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return selects;
	}

	@Override
	public boolean inputScore(String[] stu_nos, String[] scores, String p_no) {
		String sql = "UPDATE project_select SET score=? WHERE studentNo=? AND projectNo=?";
		//根据实际情况取消 AND company_sel_date IS NOT NULL 限制
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(3, p_no);
			for (int i = 0; i < stu_nos.length; i++) {
				if(stu_nos[i]==null)
					continue;
				ps.setString(1, scores[i]);
				ps.setString(2, stu_nos[i]);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public ArrayList<ProjectSelect> findScore(String p_no) {
		// 根据方案号查询已被选择的学生的成绩的sql语句
		String sql = "SELECT * FROM project_select WHERE projectNo=? AND company_sel_date IS NOT NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProjectSelect> selects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProjectSelect projectSelect = new ProjectSelect();
				projectSelect.setCompanyName(rs.getString("company_name"));
				projectSelect.setCompanySelDate(rs.getDate(""));
				projectSelect.setId(new ProjectSelectId(rs.getString("studentNo"), rs.getInt("projectNo")));
				projectSelect.setScore(rs.getString("score"));
				projectSelect.setSelReason(rs.getString("sel_reason"));
				selects.add(projectSelect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return selects;
	}

	@Override
	public int countProject() {
		// 查询方案总数的sql语句
		String sql = "SELECT COUNT(*) m FROM project";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next()) {
				m = rs.getInt("m");
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public int countCompanyProject(String company_username) {
		// 查询方案总数的sql语句
		String sql = "SELECT COUNT(*) m FROM project WHERE company_username=?";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			ps.setString(1, company_username);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next())
				m = rs.getInt("m");
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public Project findProjectByNo(String no) {
		String sql = "SELECT * FROM project WHERE No=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Project project = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return project;
	}

	@Override
	public int findMaxProjectNo(int year) {
		String sql = "SELECT MAX(`No`) m FROM project WHERE `No` LIKE '" + year + "%'";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			String m = "";
			if (rs.next()) {
				m = rs.getString("m");
				if (m == null) {
					return 0;
				}
				return Integer.parseInt(m);
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<Project> findAllStartedProject() {
		String sql = "SELECT * FROM project WHERE end_date is NULL and audit_date is not NULL AND end_date IS NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public ArrayList<Project> findAllChosenProject(String stu_no) {
		// 查询单个学生已选方案数的sql语句,未结束表明是当前年度
		String sql = "SELECT * FROM project_select,project WHERE studentNo=? AND end_date IS NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, stu_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_name"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("projectNo"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public int countAllStudentChoice(String c_name) {
		String sql = "SELECT COUNT(*) m FROM project_select WHERE company_name=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			ps.setString(1, c_name);
			rs = ps.executeQuery();
			
			int m = 0;
			if (rs.next())
				m = rs.getInt("m");
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<Project> findAllProject(int role, String company_username, PageUtils pageUtils, boolean checkState,
			String year) {
		String sql = null;
		String sql_end = null;
		if (checkState)
			sql_end = " AND audit_date IS NOT NULL LIMIT ?,?";
		else
			sql_end = " AND audit_date IS NULL LIMIT ?,?";
		int totalSize = 0;
		if (role == 9) {
			sql = "SELECT * FROM project WHERE `No` LIKE '" + year + "%'" + sql_end;
			totalSize = countProject(year, checkState, null);
			pageUtils.setTotalSize(totalSize);
		} else if (role == 1 && company_username != null) {
			sql = "SELECT * FROM project WHERE `No` LIKE '" + year + "%' AND company_username=? " + sql_end;
			totalSize = countProject(year, checkState, company_username);
			pageUtils.setTotalSize(totalSize);
		} else
			return null;
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			if (role == 9) {
				ps.setInt(1, start);
				ps.setInt(2, size);
			} else {
				ps.setString(1, company_username);
				ps.setInt(2, start);
				ps.setInt(3, size);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public int countProject(String year, boolean checkState, String company_username) {
		String sql = null;
		String sql_end = null;
		if (checkState)
			sql_end = " AND audit_date IS NOT NULL";
		else
			sql_end = " AND audit_date IS NULL";
		if (company_username == null) {
			sql = "SELECT COUNT(*) m FROM project WHERE `No` LIKE '" + year + "%'" + sql_end;
		} else
			sql = "SELECT COUNT(*) m FROM project WHERE `No` LIKE '" + year + "%' AND company_username=" + "'"
					+ company_username + "'" + sql_end;

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next()) {
				m = rs.getInt("m");
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<Project> findAllProject(String company_username) {
		String sql = "SELECT * FROM project WHERE company_username=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, company_username);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNo(String p_no, PageUtils pageUtils) {
		String sql = "SELECT * FROM project_select WHERE projectNo=? LIMIT ?,?";
		Connection connection = DbUtils.getConnection();
		int totalSize = countAllStudentChoiceByPNoAndType(p_no, "3");
		if (totalSize < 0)
			return null;
		pageUtils.setTotalSize(totalSize);
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProProSelStuView> proProSelStuViews = new ArrayList<>();
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProProSelStuView proProSelStuView = new ProProSelStuView();
				// project属性设置
				proProSelStuView.getProject().setAuditDate(rs.getDate("project_audit_date"));
				proProSelStuView.getProject().setCategory(rs.getString("project_category"));
				proProSelStuView.getProject().setCompanyTeacher(rs.getString("company_teacher"));
				proProSelStuView.getProject().setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				proProSelStuView.getProject().setCompanyUsername(rs.getString("company_name"));
				proProSelStuView.getProject().setEndDate(rs.getDate("project_end_date"));
				proProSelStuView.getProject().setGrade(rs.getInt("project_grade"));
				proProSelStuView.getProject().setIntroduction(rs.getString("project_introduction"));
				proProSelStuView.getProject().setMajor(rs.getString("project_major"));
				proProSelStuView.getProject().setName(rs.getString("project_name"));
				proProSelStuView.getProject().setNo(rs.getString("projectNo"));
				proProSelStuView.getProject().setReleaseDate(rs.getDate("project_release_date"));
				proProSelStuView.getProject().setStudentsNum(rs.getInt("project_students_num"));
				proProSelStuView.getProject().setSummary(rs.getString("project_summary"));
				// projectSelect属性设置
				proProSelStuView.getProjectSelect().setCompanyName(rs.getString("company_name"));
				proProSelStuView.getProjectSelect().setCompanySelDate(rs.getDate("company_sel_date"));
				proProSelStuView.getProjectSelect().setId(new ProjectSelectId());
				proProSelStuView.getProjectSelect().getId().setProjectNo(Integer.parseInt(rs.getString("projectNo")));
				proProSelStuView.getProjectSelect().getId().setStudentNo(rs.getString("studentNo"));
				proProSelStuView.getProjectSelect().setScore(rs.getString("score"));
				proProSelStuView.getProjectSelect().setSelReason(rs.getString("sel_reason"));
				// student属性设置
				proProSelStuView.getStudent().setClass_(rs.getString("student_class"));
				proProSelStuView.getStudent().setGender(rs.getString("student_gender"));
				proProSelStuView.getStudent().setGrade(rs.getInt("student_grade"));
				proProSelStuView.getStudent().setLearningExperience(rs.getString("student_learning_experience"));
				proProSelStuView.getStudent().setLevel(rs.getString("student_level"));
				proProSelStuView.getStudent().setMailbox(rs.getString("student_mailbox"));
				proProSelStuView.getStudent().setName(rs.getString("student_name"));
				proProSelStuView.getStudent().setNo(rs.getString("studentNo"));
				proProSelStuView.getStudent().setProfessional(rs.getString("student_professional"));
				proProSelStuView.getStudent().setResearchDirection(rs.getString("student_research_direction"));
				proProSelStuView.getStudent().setSubjectBackground(rs.getString("student_subject_background"));

				proProSelStuViews.add(proProSelStuView);
			}
			return proProSelStuViews;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return proProSelStuViews;
	}

	@Override
	public int countAllStudentChoiceByPNoAndType(String p_no, String type) {
		String sql = "SELECT COUNT(*) m FROM project_select WHERE projectNo=?";
		if (type.equals("1")) {
			sql += " AND company_sel_date IS NOT NULL";
		}
		if (type.equals("2")) {
			sql += " AND company_sel_date IS NULL";
		}
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next())
				m = rs.getInt("m");
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<ProProSelStuView> findStuScoreByPNo(String p_no, PageUtils pageUtils) {
		String sql = "SELECT * FROM project_select WHERE company_sel_date IS NOT NULL AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		int start = 0;
		int size = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<ProProSelStuView> proProSelStuViews = new ArrayList<>();
		if (pageUtils != null) {
			sql += " LIMIT ?,?";
			int totalSize = countAllSelStuByNo(p_no);
			if (totalSize < 0)
				return null;
			pageUtils.setTotalSize(totalSize);
			start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
			size = pageUtils.getPageSize();
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			if (pageUtils != null) {
				ps.setInt(2, start);
				ps.setInt(3, size);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				ProProSelStuView proProSelStuView = new ProProSelStuView();
				// project属性设置
				proProSelStuView.getProject().setAuditDate(rs.getDate("project_audit_date"));
				proProSelStuView.getProject().setCategory(rs.getString("project_category"));
				proProSelStuView.getProject().setCompanyTeacher(rs.getString("company_teacher"));
				proProSelStuView.getProject().setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				proProSelStuView.getProject().setCompanyUsername(rs.getString("company_name"));
				proProSelStuView.getProject().setEndDate(rs.getDate("project_end_date"));
				proProSelStuView.getProject().setGrade(rs.getInt("project_grade"));
				proProSelStuView.getProject().setIntroduction(rs.getString("project_introduction"));
				proProSelStuView.getProject().setMajor(rs.getString("project_major"));
				proProSelStuView.getProject().setName(rs.getString("project_name"));
				proProSelStuView.getProject().setNo(rs.getString("projectNo"));
				proProSelStuView.getProject().setReleaseDate(rs.getDate("project_release_date"));
				proProSelStuView.getProject().setStudentsNum(rs.getInt("project_students_num"));
				proProSelStuView.getProject().setSummary(rs.getString("project_summary"));
				// projectSelect属性设置
				proProSelStuView.getProjectSelect().setCompanyName(rs.getString("company_name"));
				proProSelStuView.getProjectSelect().setCompanySelDate(rs.getDate("company_sel_date"));
				proProSelStuView.getProjectSelect().setId(new ProjectSelectId());
				proProSelStuView.getProjectSelect().getId().setProjectNo(Integer.parseInt(rs.getString("projectNo")));
				proProSelStuView.getProjectSelect().getId().setStudentNo(rs.getString("studentNo"));
				proProSelStuView.getProjectSelect().setScore(rs.getString("score"));
				proProSelStuView.getProjectSelect().setSelReason(rs.getString("sel_reason"));
				// student属性设置
				proProSelStuView.getStudent().setClass_(rs.getString("student_class"));
				proProSelStuView.getStudent().setGender(rs.getString("student_gender"));
				proProSelStuView.getStudent().setGrade(rs.getInt("student_grade"));
				proProSelStuView.getStudent().setLearningExperience(rs.getString("student_learning_experience"));
				proProSelStuView.getStudent().setLevel(rs.getString("student_level"));
				proProSelStuView.getStudent().setMailbox(rs.getString("student_mailbox"));
				proProSelStuView.getStudent().setName(rs.getString("student_name"));
				proProSelStuView.getStudent().setNo(rs.getString("studentNo"));
				proProSelStuView.getStudent().setProfessional(rs.getString("student_professional"));
				proProSelStuView.getStudent().setResearchDirection(rs.getString("student_research_direction"));
				proProSelStuView.getStudent().setSubjectBackground(rs.getString("student_subject_background"));

				proProSelStuViews.add(proProSelStuView);

			}
			return proProSelStuViews;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return proProSelStuViews;
	}

	@Override
	public int countAllSelStuByNo(String no) {
		String sql = "SELECT COUNT(*) m FROM project_select WHERE company_sel_date IS NOT NULL AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next())
				m = rs.getInt("m");
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<ProjectSelect> findStuProject(String stu_no) {
		// 已被企业选择并成绩为空，当前年度正进行方案
		String sql = "SELECT * FROM project_select WHERE company_sel_date IS NOT NULL AND studentNo=? AND score IS NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProjectSelect> selects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, stu_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProjectSelect projectSelect = new ProjectSelect();
				projectSelect.setCompanyName(rs.getString("company_name"));
				projectSelect.setCompanySelDate(rs.getDate("company_sel_date"));
				projectSelect.setId(new ProjectSelectId(rs.getString("studentNo"), rs.getInt("projectNo")));
				projectSelect.setScore(rs.getString("score"));
				projectSelect.setSelReason(rs.getString("sel_reason"));
				selects.add(projectSelect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return selects;
	}

	@Override
	public ArrayList<ProProSelStuView> findAllStudentChoiceByPNoAndType(String p_no, String type, PageUtils pageUtils) {
		String sql = "";
		if (type.equals("1")) {
			sql = "SELECT * FROM project_select WHERE projectNo=? AND company_sel_date IS NOT NULL LIMIT ?,?";
		} else if (type.equals("2")) {
			sql = "SELECT * FROM project_select WHERE projectNo=? AND company_sel_date IS NULL LIMIT ?,?";
		} else if (type.equals("3")) {
			sql = "SELECT * FROM project_select WHERE projectNo=? LIMIT ?,?";
		} else {
			return null;
		}
		Connection connection = DbUtils.getConnection();
		int totalSize = countAllStudentChoiceByPNoAndType(p_no, type);
		if (totalSize < 0)
			return null;
		pageUtils.setTotalSize(totalSize);
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProProSelStuView> proProSelStuViews = new ArrayList<>();
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProProSelStuView proProSelStuView = new ProProSelStuView();
				// project属性设置
				proProSelStuView.getProject().setAuditDate(rs.getDate("project_audit_date"));
				proProSelStuView.getProject().setCategory(rs.getString("project_category"));
				proProSelStuView.getProject().setCompanyTeacher(rs.getString("company_teacher"));
				proProSelStuView.getProject().setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				proProSelStuView.getProject().setCompanyUsername(rs.getString("company_name"));
				proProSelStuView.getProject().setEndDate(rs.getDate("project_end_date"));
				proProSelStuView.getProject().setGrade(rs.getInt("project_grade"));
				proProSelStuView.getProject().setIntroduction(rs.getString("project_introduction"));
				proProSelStuView.getProject().setMajor(rs.getString("project_major"));
				proProSelStuView.getProject().setName(rs.getString("project_name"));
				proProSelStuView.getProject().setNo(rs.getString("projectNo"));
				proProSelStuView.getProject().setReleaseDate(rs.getDate("project_release_date"));
				proProSelStuView.getProject().setStudentsNum(rs.getInt("project_students_num"));
				proProSelStuView.getProject().setSummary(rs.getString("project_summary"));
				// projectSelect属性设置
				proProSelStuView.getProjectSelect().setCompanyName(rs.getString("company_name"));
				proProSelStuView.getProjectSelect().setCompanySelDate(rs.getDate("company_sel_date"));
				proProSelStuView.getProjectSelect().setId(new ProjectSelectId());
				proProSelStuView.getProjectSelect().getId().setProjectNo(Integer.parseInt(rs.getString("projectNo")));
				proProSelStuView.getProjectSelect().getId().setStudentNo(rs.getString("studentNo"));
				proProSelStuView.getProjectSelect().setScore(rs.getString("score"));
				proProSelStuView.getProjectSelect().setSelReason(rs.getString("sel_reason"));
				// student属性设置
				proProSelStuView.getStudent().setClass_(rs.getString("student_class"));
				proProSelStuView.getStudent().setGender(rs.getString("student_gender"));
				proProSelStuView.getStudent().setGrade(rs.getInt("student_grade"));
				proProSelStuView.getStudent().setLearningExperience(rs.getString("student_learning_experience"));
				proProSelStuView.getStudent().setLevel(rs.getString("student_level"));
				proProSelStuView.getStudent().setMailbox(rs.getString("student_mailbox"));
				proProSelStuView.getStudent().setName(rs.getString("student_name"));
				proProSelStuView.getStudent().setNo(rs.getString("studentNo"));
				proProSelStuView.getStudent().setProfessional(rs.getString("student_professional"));
				proProSelStuView.getStudent().setResearchDirection(rs.getString("student_research_direction"));
				proProSelStuView.getStudent().setSubjectBackground(rs.getString("student_subject_background"));

				proProSelStuViews.add(proProSelStuView);
			}
			return proProSelStuViews;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return proProSelStuViews;
	}

	@Override
	public ArrayList<String> findAllProfessional() {
		String sql = "SELECT professional FROM professional";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> professionals = new ArrayList<>();
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
				professionals.add(rs.getString("professional"));
			return professionals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return professionals;
	}
}
