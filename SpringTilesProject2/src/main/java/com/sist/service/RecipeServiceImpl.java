package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeDAO dao;

	@Override
	public List<RecipeVO> recipeListData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeListData(map);
	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return dao.recipeTotalPage();
	}

	@Override
	public List<RecipeVO> recipeFindData(String ss) {
		// TODO Auto-generated method stub
		return dao.recipeFindData(ss);
	}
	
	
}
