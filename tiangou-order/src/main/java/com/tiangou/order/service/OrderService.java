package com.tiangou.order.service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.order.pojo.OrderInfo;

public interface OrderService {
	public TiangouResult createOrder(OrderInfo orderInfo);
}
