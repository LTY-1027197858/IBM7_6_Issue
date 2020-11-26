package com.ibm.cn.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ibm.cn.model.Issue;
import com.ibm.cn.model.User;
import com.ibm.cn.service.IssueService;
import com.ibm.cn.service.UserService;


@RestController
public class IssueController {
	@Autowired
	IssueService issueService;
	UserService userService;
	
	//创建新的issue模块
	@CrossOrigin
	@PostMapping("/CreateIssue")
	public int addIssue(@RequestBody Issue issue) {
		//默认设置issue创建后状态为 待解决
		issue.setStatus("待解决");
		//获取系统时间
		issue.setCreateDate(new Date());
		//成功创建返回code：1 失败返回code：0
		int result=issueService.addIssue(issue);
		return result;
	}

	

	//修改Issue模块
	//修改解决方案
	@CrossOrigin
	@PostMapping("/updateIssue")
	public int updateIssueSolution(@RequestBody Issue issue) {
		//成功创建返回code：1 失败返回code：0
		return issueService.updateIssueSolution(issue);
	}

	//点击“提交验证按钮”
	//issue更改状态为"待验证"
	@CrossOrigin
	@PostMapping("/waitVerifyIssue")
	public int waitVerifyIssue(@RequestBody Issue issue) {
		//提交验证触发设置issue状态 待验证
		issue.setStatus("待验证");
		//成功创建返回code：1 失败返回code：0
		return issueService.updateIssueSolution(issue);
	}
	
	//点击关闭按钮模块
	//issuestatus更改为"已关闭"状态
	@CrossOrigin
	@PostMapping("/waitCloseIssue")
	public int waitCloseIssue(@RequestBody Issue issue) {
		//关闭触发设置issue状态 已关闭
		issue.setStatus("已关闭");
		//获取系统时间
		issue.setFinalDate(new Date());
		//成功关闭返回code：1 失败返回code：0
		return issueService.updateIssueClose(issue);
	}
	
	//退回修改模块
		@CrossOrigin
		@PostMapping("/waitResolveIssue")
		public int waitResolveIssue(@RequestBody Issue issue) {
			//退回修改触发设置状态 待解决
			issue.setStatus("待解决");
			//成功退回返回code：1 失败返回code：0
			return issueService.updateIssueStatus(issue);
		}


	//查询所有Issue模块
	@CrossOrigin
	@GetMapping("/IssueAll")
	public List<Issue> getAlls(){
		//调用getAllIssue获取issue全部信息装入list中
		List<Issue> list =issueService.getAllIssues();
		//返回list
		return list;
	}
	
	//模糊查询
	@CrossOrigin
	@PostMapping("/QueryIssue")
	public List<Issue> QueryIssue(@RequestBody Issue issue){
		List<Issue> listNum= issueService.QueryIssue(issue);
		
		//数据查询分页，PageNum--页数	PageSize==20
		PageHelper.startPage(issue.getPageNum(),20);
		//调用QueryIssue接口查询issue数据装入list中
		List<Issue> list= issueService.QueryIssue(issue);
//		//判断当前用户的修改权限
//		//遍list表
		for(Issue i:list) {
			
				i.setTotal(listNum.size());
		}
//		for(Issue i:list) {
//			//判断修改人
//			if(i.getAlterId().equals(issue.getUserId())) {
//				if(i.getStatus().equals("待修改"))
//				{
//					i.setPower(1);
//					
//					
//				}
//				else if(i.getStatus().equals("待验证")) {
//					i.setPower(1);
//				}
//				
//			}
//			else if(i.getAssignId().equals(issue.getUserId())) {
//				if(i.getStatus().equals("待修改"))
//				{
//					i.setPower(1);
//					
//				}
//				else if(i.getStatus().equals("待验证")) {
//					i.setPower(1);
//				}
//				
//			}
//			else {
//				i.setPower(0);
//			}
//		}
		//返回list
		return list;
	}
	
	//经理模糊查询个人报表模块
	//模糊查询
	@CrossOrigin
	@PostMapping("/QueryIssueByMananger")
	public List<Issue> QueryIssueByMananger(@RequestBody Issue issue){
		//数据查询分页，PageNum--页数	PageSize==20
		PageHelper.startPage(issue.getPageNum(),20);
		//调用QueryIssueByManager接口查询issue数据装入list中
		List<Issue> list= issueService.QueryIssueByMananger(issue);
		//返回list
		return list;
	}
	
//	//经理用户查看用户
//	@CrossOrigin
//	@GetMapping("/IssueReportForm")
//	public List<Issue> ReportForm(){
//		List<Issue> list =issueService.getAllIssues();
//		return list;
//	}	
	
	
	
}
