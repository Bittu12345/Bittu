package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CommentDataModel;


public interface CommentDataRepo extends CrudRepository<CommentDataModel, Long>{
	
	CommentDataModel findByuserName(String userName);
	

}
