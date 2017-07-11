package com.vmall.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.vmall.common.pojo.VmallResult;
import com.vmall.common.utils.CookieUtils;
import com.vmall.common.utils.JsonUtils;
import com.vmall.mapper.TbUserMapper;
import com.vmall.pojo.TbUser;
import com.vmall.pojo.TbUserExample;
import com.vmall.pojo.TbUserExample.Criteria;
import com.vmall.sso.dao.JedisClient;
import com.vmall.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private TbUserMapper userMapper;
	
	@Value("${USER_SESSION_KEY}")
	private String USER_SESSION_KEY;
	
	@Value("${SSO_USER_SESSION_KEY_EXPIRE}")
	private Integer SSO_USER_SESSION_KEY_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public VmallResult checkUser(String str, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(1==type){
			criteria.andUsernameEqualTo(str);
		}else if(2==type){
			criteria.andPhoneEqualTo(str);
		}else{
			criteria.andEmailEqualTo(str);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if(list == null || list.size() == 0){
			return VmallResult.ok(true);
		}
 		return VmallResult.ok(false);
	}

	@Override
	public VmallResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toString());
		userMapper.insert(user);
		return VmallResult.ok();
	}

	@Override
	public VmallResult userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(null == list || list.size() == 0){
			return VmallResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			System.out.println(DigestUtils.md5DigestAsHex(password.getBytes())+"-"+user.getPassword());
			return VmallResult.build(400, "用户名或密码错误");
		}
		String token = UUID.randomUUID().toString();
		user.setPassword(null);//生成token前清空user对象中的密码，防止密码泄露
		jedisClient.set(USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(USER_SESSION_KEY + ":" + token, SSO_USER_SESSION_KEY_EXPIRE);
		
		
		//写入cookie
		CookieUtils.setCookie(request, response, "VM_TOKEN", token);
		return VmallResult.ok(token);
	}

	@Override
	public VmallResult getUserByToken(String token) {
		String json = jedisClient.get(USER_SESSION_KEY + ":" +token);
		if(StringUtils.isBlank(json)){
			return VmallResult.build(500, "用户登录信息已过期");
		}
		//更新token时间
		jedisClient.expire(USER_SESSION_KEY + ":" + token, SSO_USER_SESSION_KEY_EXPIRE);
		return VmallResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

	@Override
	public VmallResult userExit(String token, HttpServletRequest request, HttpServletResponse response) {
		jedisClient.del(USER_SESSION_KEY + ":" + token);
		CookieUtils.deleteCookie(request, response, "VM_TOKEN");
		return VmallResult.ok();
	}

}
