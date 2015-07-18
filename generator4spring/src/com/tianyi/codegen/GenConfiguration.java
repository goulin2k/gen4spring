/**
 * 
 */
package com.tianyi.codegen;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

import com.tianyi.codegen.common.ConfigureException;
import com.tianyi.codegen.common.PropConfigure;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author apple
 * 
 */
public class GenConfiguration {
	private static final String NUMBER_FORMAT = "#,###.##";

	private static GenConfiguration instance;
	
	private static Configuration fmConfiguration;
	
	public final static String DEFAULT_ENCODING = "UTF-8";
	
	private final static Logger logger = Logger.getLogger(GenConfiguration.class);

	
	private GenConfiguration() {
		super();
		fmConfiguration = new Configuration();
		
	}

	/**
	 * 单实例获取
	 * 
	 * @return
	 */
	public synchronized static GenConfiguration getInstance() {
		if (instance == null)
			instance = new GenConfiguration();
		try {
			PropConfigure.init();
			
			/* 在整个应用的生命周期中,这个工作你应该只做一次。 
			创建和调整配置。 */
			logger.info("Gen Template DIR:\t" + PropConfigure.getProperty(PropConfigure.PROP_DIR_TEMPLATE));
			fmConfiguration.setClassForTemplateLoading(GenConfiguration.class, 
					PropConfigure.getProperty(PropConfigure.PROP_DIR_TEMPLATE));
			// 设置默认的
			fmConfiguration.setObjectWrapper(new DefaultObjectWrapper());
			
			/* Sets how errors will appear. Here we assume we are developing HTML pages.
				For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.	*/
			fmConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

			/* At least in new projects, specify that you want the fixes that aren't
				100% backward compatible too (these are very low-risk changes as far as the
				1st and 2nd version number remains): */
			fmConfiguration.setIncompatibleImprovements(new Version(2, 3, 20));  // FreeMarker 2.3.20 
			
			/*	Shared variables are variables that are defined for all templates. 
			You can add shared variables to the configuration with the setSharedVariable methods:
			fmConfiguration.setSharedVariable("company", "四川天翼网络服务有限责任公司.");  // Using ObjectWrapper.DEFAULT_WRAPPER 
			*/
			fmConfiguration.setLocale(Locale.CHINESE);
			fmConfiguration.setNumberFormat(NUMBER_FORMAT);
			return instance;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw(new ConfigureException(e.getMessage()));
		}

	}
	
	/**
	 * 获取单实例FreeMarker Configuration对象
	 * @return
	 */
	public Configuration getFmConfiguration() {
		return fmConfiguration;
	}

	/**
	 * 默认模板文件路径
	 * @return
	 */
	public static String getDefaultTemplateDir() {
		return PropConfigure.getProperty(PropConfigure.PROP_DIR_TEMPLATE);
	}
	
	/**
	 * @param prop
	 * @return
	 */
	public static String getConfigureProp(String prop) {
		return PropConfigure.getProperty(prop);
	}
	
	/**
	 * @return
	 */
	public static List<String> getGenModles() {
		return PropConfigure.getModleNames();
	}

}
