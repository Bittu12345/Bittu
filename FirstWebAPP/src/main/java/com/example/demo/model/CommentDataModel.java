package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommentDataModel {
	@Id
	@GeneratedValue
	private Long userId;	
	private String userName;
	private String comments;
	private long upVotes;
	
	
	
	
	
	
	public long getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(long upVotes) {
		this.upVotes = upVotes;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "CommentDataModel [userId=" + userId + ", userName=" + userName + ", comments="
				+ comments + ", upVotes=" + upVotes + "]";
	}

	
}
