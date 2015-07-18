/**
 * 
 */
package com.tianyi.codegen.common;

/**
 * @author apple
 *
 */
public class ActionModel extends GeneratorModel {
	private String servicePackage;
	
	

	public ActionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionModel(String packageName, String modelPackage,
			String className, String modelClass, String variableModel,
			String servicePackage) {
		super(packageName, modelPackage, className, modelClass, variableModel, null);
		this.servicePackage = servicePackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	
	
	
	
	
}
