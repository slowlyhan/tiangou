package com.tiangou.service;

import java.util.List;

import com.tiangou.common.pojo.TreeNode;



public interface ItemCatService {

	List<TreeNode> getItemCatList(long parentId);
}
