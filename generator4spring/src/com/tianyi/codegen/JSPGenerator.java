/**
 * 
 */
package com.tianyi.codegen;

import java.io.IOException;
import java.util.List;

import com.tianyi.codegen.common.ClassAccessor;
import com.tianyi.codegen.common.ConfigureException;
import com.tianyi.codegen.common.DisplayField;
import com.tianyi.codegen.common.GeneratException;
import com.tianyi.codegen.common.GeneratorModel;
import com.tianyi.codegen.common.PropConfigure;
import com.tianyi.codegen.common.Util;

import freemarker.template.Template;

/**
 * JSP文件生成器，根据实体类，自动生成查询列表list页面和编辑查看页面view
 * @author apple
 *
 */
public class JSPGenerator extends BaseCodeGenerator {
	
	private static JSPGenerator instance;	

	/**
	 * @param desDir
	 * @param packageName
	 * @param cfg
	 */
	public JSPGenerator(String desDir, GenConfiguration cfg) {
		super(desDir, null, cfg, 2);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cfg
	 * @return
	 */
	public synchronized static JSPGenerator getInstance(
			GenConfiguration cfg) {
		if (instance == null) {
			String desDir = GenConfiguration.getConfigureProp(PropConfigure.PROP_DIR_JSP);
			instance = new JSPGenerator(desDir, cfg);
			instance.init();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.tianyi.codegen.BaseCodeGenerator#init()
	 */
	@Override
	protected void init() {
		try {
			
			Util.mkdir(GenConfiguration.getConfigureProp(PropConfigure.PROP_DIR_JSP));
			templateNames[0] = GenConfiguration.getConfigureProp(PropConfigure.PROP_TEMPLATE_JSPLIST);
			templateNames[1] = GenConfiguration.getConfigureProp(PropConfigure.PROP_TEMPLATE_JSPVIEW);
			
			templates[0] = cfg.getFmConfiguration().getTemplate(templateNames[0],
					GenConfiguration.DEFAULT_ENCODING);
			templates[1] = cfg.getFmConfiguration().getTemplate(templateNames[1],
					GenConfiguration.DEFAULT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			throw (new ConfigureException());
		}

	}

	
	@Override
	public GeneratorModel[] buildGenModels(String srcModel) {
		GeneratorModel[] models = new GeneratorModel[templateSize];
		models[0] = buildJspModel(srcModel, "list.jsp");
		models[1] = buildJspModel(srcModel, "view.jsp");
		return models;
	}
	
	/**
	 * @param srcModel
	 * @return
	 */
	private GeneratorModel buildJspModel(String srcModel, String fileName) {
		String modelPackage = Util.getPackgeName(srcModel);
		String modelClass = Util.getClassName(srcModel);
		String varName = Util.classNameToVarName(modelClass);
		
		String genFile = varName + "/" + fileName;
		
		Util.mkdir(GenConfiguration.getConfigureProp(PropConfigure.PROP_DIR_JSP) + "/" + varName);
		GeneratorModel model = new GeneratorModel(
				null,
				modelPackage, null, modelClass, varName, genFile);
		model.setFieldList(ClassAccessor.getClassProps(srcModel));
		model.setKey(ClassAccessor.getKeyField(srcModel).getProperty());
		model.setKeyType(ClassAccessor.getKeyField(srcModel).getDataType());
		
		return model;
	}

}
