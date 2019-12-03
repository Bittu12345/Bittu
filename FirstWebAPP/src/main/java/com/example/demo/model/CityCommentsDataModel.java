package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CityCommentsDataModel {
	@Id
	@GeneratedValue
	private Long userId;	
	private String city;
	private String userName;
	private long  upVotes;
	public long getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(long upVotes) {
		this.upVotes = upVotes;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CityCommentsDataModel [userId=" + userId + ", city=" + city + ", userName=" + userName + ", upVotes="
				+ upVotes + ", comments=" + comments + "]";
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	private String comments;
	


	
}
