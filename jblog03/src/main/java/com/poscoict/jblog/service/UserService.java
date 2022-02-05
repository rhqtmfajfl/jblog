package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}


	public UserVo getUser(String id, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByIdAndPassword(id, password);
	}



}
