package com.tiangou.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.search.pojo.SearchItem;

@Service
public interface ItemService {
	TiangouResult importItems();
}
