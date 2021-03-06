package com.penghai.testoficp.management.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.penghai.testoficp.management.dao.mybatis.LinkerMapper;
import com.penghai.testoficp.management.dao.mybatis.UserMapper;
import com.penghai.testoficp.management.model.Linker;
import com.penghai.testoficp.management.model.User;
import com.penghai.testoficp.util.CommonData.CM_INFO_DATA;
/**
 * 用户相关数据访问层封装
 * @author 刘晓强
 * @Date 2017年5月5日
 */
@Repository
public class IUserDaoImpl implements CM_INFO_DATA{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LinkerMapper linkerMapper;
	/**
	 * 用户相关数据访问层封装
	 * @author 刘晓强
	 * @Date 2017年5月5日
	 */
	public Map<String, Object> saveLoginUser(User user){
		Map<String, Object> map = new HashMap<String,Object>();
		int userIsExist = userMapper.checkAddUnique(user);
		//校验邮箱是否存在
		if(userIsExist == 1){
			map.put("state", 0);
			map.put("message", REGIST_EMAIL_EXIST_INFO);
			return map;
		}
		//注册用户
		Date registrationTime = new Date();
		user.setRegistrationTime(registrationTime);
		user.setStatus(new Byte("1"));
		try {
			userMapper.insertSelective(user);
			map.put("state", 1);
			map.put("message", REGIST_SUCCESS_INFO);
			return map;
		} catch (Exception e) {
			map.put("state", 0);
			map.put("message", REGIST_ERROR_INFO);
			return map;
		}
	}
	/**
	 * 获取linker信息
	 * @return
	 */
	public List<Linker> getLinkerInfo() {
		List<Linker> linkerInfo = linkerMapper.selectAllLinkers();
		return linkerInfo;
	}
	
}
