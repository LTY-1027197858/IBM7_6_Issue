package com.ibm.cn.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cn.mapper.IssueMapper;
import com.ibm.cn.mapper.UserMapper;
import com.ibm.cn.model.Issue;
import com.ibm.cn.model.User;
@Service
public class IssueService {
	@Autowired
	IssueMapper issueMapper;
	//创建新issue
	public int addIssue(Issue issue) {
		return issueMapper.addIssue(issue);
	}
	//删除issue接口
	public int deleteIssueById(int id) {
		return issueMapper.deleteIssueById(id);
	}
	//提交验证模块接口
	public int updateIssueSolution(Issue issue) {
		return issueMapper.updateIssueSolution(issue);
	}
	//更新issue状态接口
	public int updateIssueStatus(Issue issue) {
		return issueMapper.updateIssueStatus(issue);
	}
	//关闭issue状态接口
	public int updateIssueClose(Issue issue) {
		return issueMapper.updateIssueClose(issue);
	}
	//根据issueId查询接口
	public Issue getIssueById(int id) {
		return issueMapper.getIssueById(id);
	}
	//获取issue表全部数据接口
	public List<Issue> getAllIssues() {
		return issueMapper.getAllIssues();
	}
	//模糊查询issue接口
	public List<Issue> QueryIssue(Issue issue){
		return issueMapper.QueryIssue(issue);
	}
	//经理模糊查询接口
	public List<Issue> QueryIssueByMananger(Issue issue){
		return issueMapper.QueryIssueByMananger(issue);
	}
	//邮件提醒接口
	public List<User> getAllUserEmail() {
		  return issueMapper.getAllUserEmail();
		 }
	
}
