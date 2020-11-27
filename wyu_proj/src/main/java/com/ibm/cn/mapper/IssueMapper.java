package com.ibm.cn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ibm.cn.model.Issue;
import com.ibm.cn.model.User;
@Mapper
public interface IssueMapper {
	//创建新issue接口
	public int addIssue(Issue issue);
	//删除issue接口
	public int deleteIssueById(int id);
	//提交验证模块接口
	public int updateIssueSolution(Issue issue);
	//更新issue状态接口
	public int updateIssueStatus(Issue issue);
	//关闭issue状态接口
	public int updateIssueClose(Issue issue);
	//根据issueId查询接口
	public Issue getIssueById(int id);
	//获取issue表全部数据接口
	public List<Issue> getAllIssues();
	//经理模糊查询接口
	public List<Issue> QueryIssueByMananger(Issue issue);
	//模糊查询issue接口
	public List<Issue> QueryIssue(Issue issue);
	//邮件提醒功能接口
	public List<User> getAllUserEmail();
	//public List<Issue> queryIssuesBySql(Map<String, Object> data);
}
