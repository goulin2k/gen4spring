/**
 * 
 */
package com.tianyi.codegen;

import java.io.File;
import java.io.IOException;

import com.tianyi.codegen.common.ConfigureException;
import com.tianyi.codegen.common.GeneratorModel;
import com.tianyi.codegen.common.PropConfigure;
import com.tianyi.codegen.common.Util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 业务层代码生成器
 * 
 * @author apple
 * 
 */
public class ServiceGenerator extends BaseCodeGenerator {
	private static ServiceGenerator instance;

	
	/**
	 * @param desDir
	 * @param templateFile
	 * @param packageName
	 * @param cfg
	 */
	private ServiceGenerator(String desDir, 
			String packageName, GenConfiguration cfg) {
		super(desDir, packageName, cfg, 2);
	}	
	

	/* (non-Javadoc)
	 * @see com.tianyi.codegen.BaseCodeGenerator#init()
	 */
	@Override
	protected void init() {
		try {
			
			createPackageDir(GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICE));
			createPackageDir(GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICEIMPL));
			templateNames[0] = GenConfiguration.getConfigureProp(PropConfigure.PROP_TEMPLATE_SERVICE);
			templateNames[1] = GenConfiguration.getConfigureProp(PropConfigure.PROP_TEMPLATE_SERVICEIMPL);
			
			templates[0] = cfg.getFmConfiguration().getTemplate(templateNames[0],
					GenConfiguration.DEFAULT_ENCODING);
			templates[1] = cfg.getFmConfiguration().getTemplate(templateNames[1],
					GenConfiguration.DEFAULT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			throw (new ConfigureException());
		}
		
	}


	/**
	 * @param cfg
	 * @return
	 */
	public synchronized static ServiceGenerator getInstance(
			GenConfiguration cfg) {
		if (instance == null) {
			String desDir = GenConfiguration.getConfigureProp(PropConfigure.PROP_DIR_GEN);
			String pkName = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICE);
			instance = new ServiceGenerator(desDir, pkName, cfg);
			instance.init();
		}
		return instance;
	}

	
	
	@Override
	public GeneratorModel[] buildGenModels(String srcModel) {
		GeneratorModel[] models = new GeneratorModel[templateSize];
		models[0] = buildInterfGenModel(srcModel);
		models[1] = buildImplGenModel(srcModel);
		return models;
	}

	/**
	 * @param srcModel
	 * @return
	 */
	protected GeneratorModel buildInterfGenModel(String srcModel) {
		String modelPackage = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_MODEL);
		String modelClass = Util.getClassName(srcModel);
		String varName = Util.classNameToVarName(modelClass);
		String className = Util.getClassName(srcModel) + SUFFIX_SERVICE_CLASS;
		
		String genFile = Util.packageToPath(GenConfiguration.
				getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICE)) +
				"/" + className + SUFFIX_JAVAFILE;
		GeneratorModel model = new GeneratorModel(this.packageName, modelPackage,  
				className, modelClass, varName, genFile);
		
		return model;
	}
	
	/**
	 * @param srcModel
	 * @return
	 */
	protected GeneratorModel buildImplGenModel(String srcModel) {
		String modelPackage = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_MODEL);
		String modelClass = Util.getClassName(srcModel);
		String varName = Util.classNameToVarName(modelClass);
		String className = Util.getClassName(srcModel) + SUFFIX_SERVICEIMPL_CLASS;
		String daoClass = modelClass + GenConfiguration.getConfigureProp(PropConfigure.PROP_SUFFIX_DAO);
		
		String genFile = Util.packageToPath(GenConfiguration.
				getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICEIMPL)) +
				"/" + className + SUFFIX_JAVAFILE;
		
		GeneratorModel model = new GeneratorModel(
				GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICEIMPL), 
				modelPackage, className, modelClass, varName, genFile);
		
		model.setDaoClass(daoClass);
		model.setDaoVariable(Util.classNameToVarName(daoClass));
		model.setDaoPackage(GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_DAO));
		model.setInterfaceName(Util.getClassName(srcModel) + SUFFIX_SERVICE_CLASS);
		model.setServicePackage(packageName);
		model.setVariable(Util.classNameToVarName(model.getInterfaceName()));
		return model;
	}
	
}
