/**
 * 
 */
package com.tianyi.model;

import java.util.Date;

import com.tianyi.codegen.common.DisplayAnnotation;

/**
 * @author apple
 *
 */
public class Demo {
	
	@DisplayAnnotation(name="名称")
	private String name;
	@DisplayAnnotation(name="ID", isListInTable=false, isKey=true)
	private String id;
	@DisplayAnnotation(name="描述")
	private String description;
	@DisplayAnnotation(name="添加日期")
	private Date addTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
