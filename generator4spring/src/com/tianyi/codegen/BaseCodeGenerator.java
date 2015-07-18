package com.tianyi.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tianyi.codegen.common.ConfigureException;
import com.tianyi.codegen.common.GeneratException;
import com.tianyi.codegen.common.GeneratorModel;
import com.tianyi.codegen.common.PropConfigure;
import com.tianyi.codegen.common.Util;
import com.tianyi.codegen.demo.UpperDirective;
import com.tianyi.codegen.directive.HtmlTableFieldDirective;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author apple
 *
 */
public abstract class BaseCodeGenerator {
	protected static final String SUFFIX_JAVAFILE = ".java";
	protected static final String SUFFIX_JSPFILE = ".jsp";
	
	protected static final String SUFFIX_SERVICEIMPL_CLASS = "ServiceImpl";
	protected static final String SUFFIX_SERVICE_CLASS = "Service";	
	protected static final String SUFFIX_ACTION_CLASS = "Controller";

	protected static final String SUFFIX_IMPL = "Impl";
	
	protected String desDir; // 自动生成的代码文件夹
	protected String packageName;
	
	protected GenConfiguration cfg;
	protected Template[] templates;
	protected static String[] templateNames;
	protected static int templateSize = 1;
	
	protected static Logger logger = Logger.getLogger(BaseCodeGenerator.class);
	
	private BaseCodeGenerator() {
		super();
	}

	/**
	 * @param desDir
	 * @param templateFile
	 * @param cfg
	 */
	protected BaseCodeGenerator(String desDir, 
			String packageName,
			GenConfiguration cfg,
			int templateSize) {
		super();
		this.desDir = desDir;
		this.packageName  = packageName;
		this.cfg = cfg;
		this.templateSize = templateSize;
		this.templateNames = new String[templateSize];
		this.templates = new Template[templateSize];
	}
	
	/**
	 * 模板环境初始化
	 */
	protected abstract void init();
	
	/**
	 * @param srcModel
	 * @return
	 */
	public abstract GeneratorModel[] buildGenModels(String srcModel);

	
	/**
	 * 根据实体类，自动生成Service、Controller和jsp页面代码
	 * @param srcModel		模板类，包含包名全路径
	 */
	public void generate(String srcModel) {
		generatorFiles(srcModel);
	}
	
	
	/**
	 * 生成目标源代码文件
	 * @param srcModel
	 */
	protected void generatorFiles(String srcModel) {
		GeneratorModel[] models = buildGenModels(srcModel);
		try {
			for (int i = 0; i < templates.length; i++) {
				File file = createFile(models[i].getGenerateFile());
				generateCodeFile(file, models[i], templates[i]);
			}
		} catch (IOException ie) {
			throw (new GeneratException("IO Exception:\t" + ie.getMessage()));
		} catch (TemplateException te) {
			throw (new GeneratException("Generate Template Exception" + te.getMessage()));
		}
	}
	
	
	
	/**
	 * 根据包名称创建代码路径
	 */
	protected void createPackageDir(String packageName) {
		// 创建目标文件夹
		Util.mkdir(desDir);
		String packPah = desDir + "/" + Util.packageToPath(packageName);
		Util.mkdir(packPah);
	}

	
	/**
	 * 如果生成源代码包路径目录不存在，创建该目录
	 * @param srcModel
	 * @return
	 * @throws IOException
	 */
	protected File createFile(String fileName) throws IOException{
		
		File file = new File(desDir + "/" + fileName);
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
	
	
	/**
	 * @param file
	 * @param model
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateCodeFile(File file, GeneratorModel model, Template template) throws IOException,
			TemplateException {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("model", model);

		FileWriter out = new FileWriter(file.getAbsolutePath(), false);
		template.process(root, out);
		out.flush();
		logger.info("Generated File:\t" + file.getAbsolutePath());
	}

}
