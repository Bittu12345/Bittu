package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CityCommentsDataModel;

public interface CityCommentDataRepo extends CrudRepository<CityCommentsDataModel, Long>{
	
	List<CityCommentsDataModel> findBycity(String city);
	
	@Query ("from CommentDataModel where username= ?1 And city = ?2")
	List<CityCommentsDataModel> findUserInCity(String username, String City);
	
}
