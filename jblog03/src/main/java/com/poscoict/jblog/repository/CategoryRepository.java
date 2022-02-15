package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	public boolean insert_category(CategoryVo vo) {
		// TODO Auto-generated method stub
		

		return 1 == sqlSession.insert("category.insert_category", vo);
	}
	public CategoryVo select_category_no(String blog_user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.select_category_no", blog_user_id);
	}
	public List<CategoryVo> select_category_all(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("category.select_category_all", id);
	}
	public CategoryVo select_no_name(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.select_no_name", name);
	}
	public boolean delete_category(int no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.delete_category", no);
	}
	public boolean insert_default_category(CategoryVo cvo) {
		// TODO Auto-generated method stub
		return 1 == sqlSession.insert("category.insert_default_category", cvo);
	}

}
