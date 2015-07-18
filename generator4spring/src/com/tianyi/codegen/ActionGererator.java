package com.tianyi.codegen;

import java.io.File;
import java.io.IOException;

import com.tianyi.codegen.common.ActionModel;
import com.tianyi.codegen.common.ClassAccessor;
import com.tianyi.codegen.common.ConfigureException;
import com.tianyi.codegen.common.GeneratException;
import com.tianyi.codegen.common.GeneratorModel;
import com.tianyi.codegen.common.PropConfigure;
import com.tianyi.codegen.common.Util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ActionGererator extends BaseCodeGenerator {
	private static ActionGererator instance;
	
	/**
	 * @param desDir
	 * @param packageName
	 * @param cfg
	 */
	private ActionGererator(String desDir, 
			String packageName, GenConfiguration cfg) {
		super(desDir, packageName, cfg, 1);
	}

	@Override
	protected void init() {
		try {
			
			createPackageDir(GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_ACTION));
			templateNames[0] = GenConfiguration.getConfigureProp(PropConfigure.PROP_TEMPLATE_ACTION);			
			templates[0] = cfg.getFmConfiguration().getTemplate(templateNames[0],
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
	public synchronized static ActionGererator getInstance(
			GenConfiguration cfg) {
		if (instance == null) {
			String desDir = GenConfiguration.getConfigureProp(PropConfigure.PROP_DIR_GEN);
			String pkName = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_ACTION);
			instance = new ActionGererator(desDir, pkName, cfg);
			instance.init();
		}
		return instance;
	}


	private GeneratorModel buildModel(String srcModel) {
		String modelPackage = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_MODEL);
		String modelClass = Util.getClassName(srcModel);
		String varName = Util.classNameToVarName(modelClass);
		String className = Util.getClassName(srcModel) + SUFFIX_ACTION_CLASS;
		String servicePackage = GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_SERVICE);
		
		String genFile = Util.packageToPath(
				GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_ACTION)) +
				"/" + className + SUFFIX_JAVAFILE;
		GeneratorModel model = new GeneratorModel(
				GenConfiguration.getConfigureProp(PropConfigure.PROP_PACKAGE_ACTION), 
				modelPackage, className, modelClass, varName, genFile);
		model.setServicePackage(servicePackage);
		
		model.setFieldList(ClassAccessor.getClassProps(srcModel));
		model.setKey(ClassAccessor.getKeyField(srcModel).getProperty());
		model.setKeyType(ClassAccessor.getKeyField(srcModel).getDataType());
		return model;
	}

	@Override
	public GeneratorModel[] buildGenModels(String srcModel) {
		GeneratorModel[] models = new GeneratorModel[templateSize];
		models[0] = buildModel(srcModel);
		return models;
	}
	
	
	
}
