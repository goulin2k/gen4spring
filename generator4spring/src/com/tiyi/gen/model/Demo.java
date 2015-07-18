package com.tiyi.gen.model;

import java.util.Date;

import com.tianyi.codegen.common.DisplayAnnotation;

public class Demo {
	@DisplayAnnotation(name="", isEditable=false, isListInTable=true, isKey=true)
    private Integer id;
	
	@DisplayAnnotation(name="名称", isEditLink=true)
    private String name;
	@DisplayAnnotation(name="描述")
    private String description;
	@DisplayAnnotation(name="添加日期")
    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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