package com.tiangou.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.portal.pojo.CartItem;

@Service
public interface CartService {
	public TiangouResult addCart(Long itemId, Integer num, 
			HttpServletRequest request, HttpServletResponse response);
	
	public List<CartItem> getCartItems(HttpServletRequest request);
	public TiangouResult updateCartItem(long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response);
	
	public TiangouResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
