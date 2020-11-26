package com.ibm.cn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

// iTitle, createDate, iType, ilevel, aVersion, planDate, finalDate, reSteps, status, solution, assignId, alterId
public class Issue {
	private Integer issueId;		//数据库唯一ID
	private String iTitle;			//ISSUE题目
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date createDate;		//ISSUE创建时间
	private String iType;			//ISSUE类型
	private String ilevel;			//ISSUE等级
	private String aVersion;		//ISSUE影响版本
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date planDate;			//ISSUE计划修改时间
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date finalDate;			//ISSUE实际完成时间
	private String reSteps;			//ISSUE重现步骤
	private String status;			//ISSUE状态
	private String solution;		//ISSUE解决方案
	private String assignId;		//创建人
	private String alterId;			//指派修改人
	//分页的参数
	private int pageNum;			//页数
	private int pageSize;			//每页的条数
	//模糊查询的起始和终止时间
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date createStartTime;	//模糊查询  issue创建时间start条件
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date createEndTime;		//模糊查询  issue创建时间end条件
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date planStartTime;		//模糊查询  issue计划时间start条件
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date planEndTime;		//模糊查询  issue计划时间end条件
	//Issue修改权限的参数和Issue的用户Id的判断的参数
	private int power;
	private String userId;
	
	//总条数
	private int total;
	
	
	
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getiTitle() {
		return iTitle;
	}
	public void setiTitle(String iTitle) {
		this.iTitle = iTitle;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getiType() {
		return iType;
	}
	public void setiType(String iTyepe) {
		this.iType = iTyepe;
	}
	public String getIlevel() {
		return ilevel;
	}
	public void setIlevel(String ilevel) {
		this.ilevel = ilevel;
	}
	public String getaVersion() {
		return aVersion;
	}
	public void setaVersion(String aVersion) {
		this.aVersion = aVersion;
	}
	public String getReSteps() {
		return reSteps;
	}
	public void setReSteps(String reSteps) {
		this.reSteps = reSteps;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getAssignId() {
		return assignId;
	}
	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}
	public String getAlterId() {
		return alterId;
	}
	public void setAlterId(String alterId) {
		this.alterId = alterId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Date getCreateStartTime() {
		return createStartTime;
	}
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}
	public Date getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
	public Date getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	public Date getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	

}
