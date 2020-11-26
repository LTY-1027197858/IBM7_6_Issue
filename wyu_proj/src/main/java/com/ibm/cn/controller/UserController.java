package com.ibm.cn.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ibm.cn.model.User;
import com.ibm.cn.model.VerifyCode;
import com.ibm.cn.mapper.IVerifyCodeGen;
import com.ibm.cn.model.Issue;
import com.ibm.cn.service.UserService;
import com.ibm.cn.service.IssueService;
import com.ibm.cn.service.SimpleCharVerifyCodeGenImpl;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	// 验证码缓存变量
	 private String code;
	
	//用户注册模块
	@CrossOrigin
	@PostMapping("/Register")
	 public int addUser(@RequestBody User user) {
		//调用getAllUsers接口获取user表所有数据
		List<User> list =userService.getAllUsers();
		//遍历list
	    for(User u:list) {
	    //判断username是否已经存在
	     if(u.getUsername().equals(user.getUsername())) {
	    //存在返回code：0
	      return 0;
	     }
	    }
	    
	    //默认设置用户状态激活 身份普通用户
	    user.setuStatus("激活");
	    user.setIdentity("普通用户");
	    //获取系统时间
	    user.setSignInDate(new Date());
	    //调用addUser接口插入数据到数据库
	    userService.addUser(user);
	    //成功创建插入返回code：1
	    return 1; 
	 }
	
	
	//用户登录模块
	@CrossOrigin
	@PostMapping("/Login")
	public String Login( @RequestBody User user){
		//设置返回标识
		String flag="";
		//调用getAllUsers接口获取user表所有数据
		List<User> list =userService.getAllUsers();
		if((user.getCode().toUpperCase()).equals(code)) {
			//遍历list表
			for(User u:list) {
				//判断用户名和密码是否正确
				if((u.getUsername()).equals(user.getUsername())&&(u.getPasswd()).equals(user.getPasswd())){
					//判断账号状态是否激活
					if(u.getuStatus().equals("注销")) {
						 //注销返回code：651
						 flag="651";
						 break;
					 }
					//判断账号的身份 身份为经理返回code：602
					  if(u.getIdentity().equals("经理")) {
						  flag="602";
						  break;
					   }
					  //判断身份为 超级管理员则返回code：603
					  else if(u.getIdentity().equals("Admin")) {
						  flag="603";
						  break;
					   }
					  //判断身份为 普通用户返回code：601
					  else {
						  flag="601";
						  break;
					   }
					}
				else if((u.getUsername()).equals(user.getUsername())){
					//判断密码是否正确
					if(!(u.getPasswd()).equals(user.getPasswd())) {
						//密码错误返回code：652
						flag="652";
						break;
					}	    
				}
				else if(!(u.getUsername()).equals(user.getUsername())) {
					   //用户名不存在返回code：653
					   flag="653";
				}
			}
		}
		else {
			flag="611";
		}
		return flag;
	}

	//修改个人信息模块
	@CrossOrigin
	@PostMapping("/updateUser")
	public int updateUserById(@RequestBody User user) {
		//修改成功返回code：1  失败返回code：0
		return userService.updateUserById(user);
	}
	
	//修改用户的身份模块
	@CrossOrigin
	@PostMapping("/updateUserIdentity")
	public int updateUserIdentity(@RequestBody User user) {
		//修改成功返回code：1  失败返回code：0
		return userService.updateUserIdentity(user);
	}
	
	//修改用户的状态模块
	@CrossOrigin
	@PostMapping("/updateUserStatus")
	public int updateUserStatus(@RequestBody User user) {
		//修改成功返回code：1  失败返回code：0
		return userService.updateUserStatus(user);
	}
	
	//查询所有用户模块
	@CrossOrigin
	@GetMapping("/UserAll")
	public List<User> getAlls(){
		//调用getAllUsers接口获取user表所有数据
		List<User> list =userService.getAllUsers();
		//返回list前端
		return list;
	}
	
	//查询所有用户的usernames
		@CrossOrigin
		@GetMapping("/getAllUsernames")
		public List<User> getAllUsernames(User user){
			//设置查询条件只查询身份为 普通用户 的usernames
			user.setIdentity("普通用户");
			//调用getAllUsernames接口获取user表所有usernames装入list中
			List<User> list =userService.getAllUsernames(user);
			//返回list
			return list;
		}

	//经理用户查询员工用户报表模块
	@CrossOrigin
	@PostMapping("/getUserReport")
	public List<User> getUser(@RequestBody User user){
		List<User> listNum =userService.getUser(user);
		
		//数据查询分页，PageNum--页数	PageSize==20
		PageHelper.startPage(user.getPageNum(),20);
		//调用getUser接口  模糊查询 条件：username name   结果装入list中
		List<User> list =userService.getUser(user);
		
		//调用getIssues接口获取issue表所有数据
		List<Issue> issueList=userService.getIssues();
		//初始化
		int CreateINum=0; 		//用户创建issue数
		int ReceiveINum=0;		//用户收到issue数
		int AlterINum=0;		//用户关闭issue数
		int iPerCom1=0;			//用户issue完成率
		String iPerCom="";
		
		for(User us:list) {
			//
			us.setTotal(listNum.size());
		}
		
		
		
		//遍历list中用户--符合用户模糊查询条件的用户
		for(User u:list) {
			//遍历issue表
			for(Issue i: issueList) {
				//判断用户名和创建人名字是否相同
				if(u.getUsername().equals(i.getAssignId())) {
					//用户创建issue数加1
					CreateINum=CreateINum+1;
				}
				//判断用户名和修改人名字是否相同
				 if(u.getUsername().equals(i.getAlterId())) {
					//用户收到issue数加1
					ReceiveINum=ReceiveINum+1;
					//判断issue状态是否已关闭
						if(i.getStatus().equals("已关闭")) {
							//用户关闭issue数加1
							AlterINum=AlterINum+1;
					}
				 }	
			}
			//防止分母为0
			if(ReceiveINum!=0)
			{
				//计算用户完成率
				iPerCom1=(AlterINum*100)/(ReceiveINum);
			}	
			//若分母即已关闭issue数为0则完成率置0
			else {
				iPerCom1=0;
			}
			//载入计数
			iPerCom=iPerCom1+"%";
			u.setCreateINum(CreateINum);
			u.setReceiveINum(ReceiveINum);
			u.setAlterINum(AlterINum);
			u.setIPerCom(iPerCom);
			//计数器清零
			CreateINum=0;
			ReceiveINum=0;
			AlterINum=0;
			iPerCom1=0;
		}
		return list;
	}
	
	


	//超级用户的模糊查询	
	@CrossOrigin
	@PostMapping("/getUserAdmin")
	public List<User> getUserAdmin(@RequestBody User user){
		List<User> listNum =userService.getUserAdmin(user);
		
		//数据查询分页，PageNum--页数	PageSize==20
		PageHelper.startPage(user.getPageNum(),20);
		//调用getUserAdmin接口获取符合模糊查询条件的user数据 条件：username name
		List<User> list =userService.getUserAdmin(user);
		//返回list
		int total=0;
		
		
		for(User uadmin:list) {
			
			uadmin.setTotal(listNum.size());
			
			
		}
		return list;
	}	
	
	//验证码
	 @CrossOrigin
	 @GetMapping("/verifyCode")
	 public String verifyCode(HttpServletRequest request, HttpServletResponse response) {
	  IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
	  try {
	   //设置长宽
	   VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
	    code = verifyCode.getCode();
	   //将VerifyCode绑定session
	   request.getSession().setAttribute("VerifyCode", code);
	   //设置响应头
	   response.setHeader("Pragma", "no-cache");
	   //设置响应头
	   response.setHeader("Cache-Control", "no-cache");
	   //在代理服务器端防止缓冲
	   response.setDateHeader("Expires", 0);
	   //设置响应内容类型
	   response.setContentType("image/jpeg");
	   response.getOutputStream().write(verifyCode.getImgBytes());
	   response.getOutputStream().flush();
	   //System.out.println(code);
	  }
	  catch (IOException e) {
	  }
	  return code;  
	 }
	}
	
	

