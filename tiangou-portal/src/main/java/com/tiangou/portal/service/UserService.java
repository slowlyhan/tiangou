package com.tiangou.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.tiangou.pojo.TbUser;


@Service
public interface UserService {
	public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) ;
}
