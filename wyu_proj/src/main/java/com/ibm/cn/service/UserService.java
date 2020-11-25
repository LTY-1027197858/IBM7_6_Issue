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
public class UserService {
	@Autowired
	UserMapper userMapper;
	//用户注册
	public int addUser(User user) {
		return userMapper.addUser(user);
	}
	//删除用户账号
	public int deleteUserById(int id) {
		return userMapper.deleteUserById(id);
	}
	//更新用户信息
	public int updateUserById(User user) {
		return userMapper.updateUserById(user);
	}
	//经理调用用户模糊查询 条件 username name
	public List<User> getUser(User user) {
		return userMapper.getUser(user);
	}
	//超级用户调用用户模糊查询 条件 username name
	public List<User> getUserAdmin(User user) {
		return userMapper.getUserAdmin(user);
	}
	//获取user表的所有数据 接口
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	//获取user表所有的username接口
	public List<User> getAllUsernames(User user) {
		return userMapper.getAllUsernames(user);
	}
	//获取issue表的所有数据接口
	public List<Issue> getIssues() {
		return userMapper.getIssues();
	}
	//更新用户账号状态接口
	public int updateUserStatus(User user) {
		return userMapper.updateUserStatus(user);
	}
	//更新用户账号身份接口
	public int updateUserIdentity(User user) {
		return userMapper.updateUserIdentity(user);
	}
}
