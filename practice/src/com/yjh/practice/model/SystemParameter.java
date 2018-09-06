package com.yjh.practice.model;

import java.sql.Date;


public class SystemParameter implements java.io.Serializable {

	// Fields

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String adminUsername;
		private String adminPassword;
		private String invitationCode;
		private Date releaseProjectStartDate;
		private Date releaseProjectEndDate;
		private Date studentSelStartDate;
		private Date studentSelEndDate;
		private Integer studentSelMaxnum;

		// Constructors

		/** default constructor */
		public SystemParameter() {
		}

		/** minimal constructor */
		public SystemParameter(String adminUsername, String adminPassword,
				String invitationCode, Integer studentSelMaxnum) {
			this.adminUsername = adminUsername;
			this.adminPassword = adminPassword;
			this.invitationCode = invitationCode;
			this.studentSelMaxnum = studentSelMaxnum;
		}

		/** full constructor */
		public SystemParameter(String adminUsername, String adminPassword,
				String invitationCode, Date releaseProjectStartDate,
				Date releaseProjectEndDate, Date studentSelStartDate,
				Date studentSelEndDate, Integer studentSelMaxnum) {
			this.adminUsername = adminUsername;
			this.adminPassword = adminPassword;
			this.invitationCode = invitationCode;
			this.releaseProjectStartDate = releaseProjectStartDate;
			this.releaseProjectEndDate = releaseProjectEndDate;
			this.studentSelStartDate = studentSelStartDate;
			this.studentSelEndDate = studentSelEndDate;
			this.studentSelMaxnum = studentSelMaxnum;
		}

		// Property accessors

		public String getAdminUsername() {
			return this.adminUsername;
		}

		public void setAdminUsername(String adminUsername) {
			this.adminUsername = adminUsername;
		}

		public String getAdminPassword() {
			return this.adminPassword;
		}

		public void setAdminPassword(String adminPassword) {
			this.adminPassword = adminPassword;
		}

		public String getInvitationCode() {
			return this.invitationCode;
		}

		public void setInvitationCode(String invitationCode) {
			this.invitationCode = invitationCode;
		}

		public Date getReleaseProjectStartDate() {
			return this.releaseProjectStartDate;
		}

		public void setReleaseProjectStartDate(Date releaseProjectStartDate) {
			this.releaseProjectStartDate = releaseProjectStartDate;
		}

		public Date getReleaseProjectEndDate() {
			return this.releaseProjectEndDate;
		}

		public void setReleaseProjectEndDate(Date releaseProjectEndDate) {
			this.releaseProjectEndDate = releaseProjectEndDate;
		}

		public Date getStudentSelStartDate() {
			return this.studentSelStartDate;
		}

		public void setStudentSelStartDate(Date studentSelStartDate) {
			this.studentSelStartDate = studentSelStartDate;
		}

		public Date getStudentSelEndDate() {
			return this.studentSelEndDate;
		}

		public void setStudentSelEndDate(Date studentSelEndDate) {
			this.studentSelEndDate = studentSelEndDate;
		}

		public Integer getStudentSelMaxnum() {
			return this.studentSelMaxnum;
		}

		public void setStudentSelMaxnum(Integer studentSelMaxnum) {
			this.studentSelMaxnum = studentSelMaxnum;
		}

		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof SystemParameter))
				return false;
			SystemParameter castOther = (SystemParameter) other;

			return ((this.getAdminUsername() == castOther.getAdminUsername()) || (this
					.getAdminUsername() != null
					&& castOther.getAdminUsername() != null && this
					.getAdminUsername().equals(castOther.getAdminUsername())))
					&& ((this.getAdminPassword() == castOther.getAdminPassword()) || (this
							.getAdminPassword() != null
							&& castOther.getAdminPassword() != null && this
							.getAdminPassword()
							.equals(castOther.getAdminPassword())))
					&& ((this.getInvitationCode() == castOther.getInvitationCode()) || (this
							.getInvitationCode() != null
							&& castOther.getInvitationCode() != null && this
							.getInvitationCode().equals(
									castOther.getInvitationCode())))
					&& ((this.getReleaseProjectStartDate() == castOther
							.getReleaseProjectStartDate()) || (this
							.getReleaseProjectStartDate() != null
							&& castOther.getReleaseProjectStartDate() != null && this
							.getReleaseProjectStartDate().equals(
									castOther.getReleaseProjectStartDate())))
					&& ((this.getReleaseProjectEndDate() == castOther
							.getReleaseProjectEndDate()) || (this
							.getReleaseProjectEndDate() != null
							&& castOther.getReleaseProjectEndDate() != null && this
							.getReleaseProjectEndDate().equals(
									castOther.getReleaseProjectEndDate())))
					&& ((this.getStudentSelStartDate() == castOther
							.getStudentSelStartDate()) || (this
							.getStudentSelStartDate() != null
							&& castOther.getStudentSelStartDate() != null && this
							.getStudentSelStartDate().equals(
									castOther.getStudentSelStartDate())))
					&& ((this.getStudentSelEndDate() == castOther
							.getStudentSelEndDate()) || (this
							.getStudentSelEndDate() != null
							&& castOther.getStudentSelEndDate() != null && this
							.getStudentSelEndDate().equals(
									castOther.getStudentSelEndDate())))
					&& ((this.getStudentSelMaxnum() == castOther
							.getStudentSelMaxnum()) || (this.getStudentSelMaxnum() != null
							&& castOther.getStudentSelMaxnum() != null && this
							.getStudentSelMaxnum().equals(
									castOther.getStudentSelMaxnum())));
		}

		public int hashCode() {
			int result = 17;

			result = 37
					* result
					+ (getAdminUsername() == null ? 0 : this.getAdminUsername()
							.hashCode());
			result = 37
					* result
					+ (getAdminPassword() == null ? 0 : this.getAdminPassword()
							.hashCode());
			result = 37
					* result
					+ (getInvitationCode() == null ? 0 : this.getInvitationCode()
							.hashCode());
			result = 37
					* result
					+ (getReleaseProjectStartDate() == null ? 0 : this
							.getReleaseProjectStartDate().hashCode());
			result = 37
					* result
					+ (getReleaseProjectEndDate() == null ? 0 : this
							.getReleaseProjectEndDate().hashCode());
			result = 37
					* result
					+ (getStudentSelStartDate() == null ? 0 : this
							.getStudentSelStartDate().hashCode());
			result = 37
					* result
					+ (getStudentSelEndDate() == null ? 0 : this
							.getStudentSelEndDate().hashCode());
			result = 37
					* result
					+ (getStudentSelMaxnum() == null ? 0 : this
							.getStudentSelMaxnum().hashCode());
			return result;
		}

}