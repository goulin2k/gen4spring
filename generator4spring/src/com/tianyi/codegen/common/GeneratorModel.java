/**
 * 
 */
package com.tianyi.codegen.common;

import java.util.List;

import com.tianyi.common.BaseObject;

/**
 * @author apple
 *
 */
public class GeneratorModel extends BaseObject {
	
	@DisplayAnnotation(name="包名")
	private String packageName;
	
	private String className;
	private String variableModel;
	private String modelPackage;
	private String modelClass;
	private String variable;
	
	@DisplayAnnotation(name="ID", isKey=true)
	private String key;
	private String keyType;
	
	private String generateFile;

	private List<DisplayField> fieldList;
	
	private String interfaceName;
	private String daoPackage;
	private String daoClass;
	private String daoVariable;
	private String servicePackage;

	public GeneratorModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public GeneratorModel(String packageName, String modelPackage,
			String className, String modelClass, String variableModel,
			String generateFile) {
		super();
		this.packageName = packageName;
		this.modelPackage = modelPackage;
		this.className = className;
		this.modelClass = modelClass;
		this.variableModel = variableModel;
		this.generateFile = generateFile;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getVariableModel() {
		return variableModel;
	}


	public void setVariableModel(String variable) {
		this.variableModel = variable;
	}
	
	public String getModelPackage() {
		return modelPackage;
	}


	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	
	public String getModelClass() {
		return modelClass;
	}


	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	

	public String getVariable() {
		return variable;
	}


	public void setVariable(String variable) {
		this.variable = variable;
	}


	public String getGenerateFile() {
		return generateFile;
	}


	public void setGenerateFile(String generateFile) {
		this.generateFile = generateFile;
	}


	public List<DisplayField> getFieldList() {
		return fieldList;
	}


	public void setFieldList(List<DisplayField> fieldList) {
		this.fieldList = fieldList;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}


	public String getDaoPackage() {
		return daoPackage;
	}


	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}


	public String getDaoClass() {
		return daoClass;
	}


	public void setDaoClass(String daoClass) {
		this.daoClass = daoClass;
	}


	public String getDaoVariable() {
		return daoVariable;
	}


	public void setDaoVariable(String daoVariable) {
		this.daoVariable = daoVariable;
	}


	public String getServicePackage() {
		return servicePackage;
	}


	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getKeyType() {
		return keyType;
	}


	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	

}
