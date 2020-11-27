package com.ibm.cn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ibm.cn.model.Issue;
import com.ibm.cn.model.User;
@Mapper
public interface UserMapper {
	//用户注册接口
	public int addUser(User user);
	//删除用户账号接口
	public int deleteUserById(int id);
	//更新用户信息接口
	public int updateUserById(User user);
	//经理调用用户模糊查询 条件 username name 接口
	public List<User> getUser(User user);
	//超级用户调用用户模糊查询 条件 username name 接口
	public List<User> getUserAdmin(User user);
	//获取user表的所有数据 接口
	public List<User> getAllUsers();
	//获取user表所有的username接口
	public List<User> getAllUsernames(User user);
	//获取issue表的所有数据接口
	public List<Issue> getIssues();
	//更新用户账号状态接口
	public int updateUserStatus(User user);
	//用户账号下线
	public int updateUserLogStatus(User user);
	//更新用户账号身份接口
	public int updateUserIdentity(User user);
}
