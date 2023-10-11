package com.example.myapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("msg")
	private String msg;

	@SerializedName("test")
	private List<TestItem> test;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setTest(List<TestItem> test){
		this.test = test;
	}

	public List<TestItem> getTest(){
		return test;
	}
}