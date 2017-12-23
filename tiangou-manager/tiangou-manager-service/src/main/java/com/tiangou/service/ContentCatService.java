package com.tiangou.service;

import java.util.List;


import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.common.pojo.TreeNode;

public interface ContentCatService {

	List<TreeNode> getContentCatList(long parentId);
	TiangouResult createNode(long parentId, String name);
}
