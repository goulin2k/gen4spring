/**
 * 
 */
package com.tianyi.codegen.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author apple
 * 属性配置文件加载和管理
 *
 */
public class PropConfigure {
	private static Logger logger = Logger.getLogger(PropConfigure.class);

	public static final String PROPERTIES_FILE = 		"resources/generator_config.xml";
	public static final String PROP_DIR_GEN = 			"generate-dir-source";
	public static final String PROP_DIR_JSP = 			"generate-dir-web";
	public static final String PROP_DIR_TEMPLATE = 		"generate-dir-template";
	
	public static final String PROP_PACKAGE_ACTION = 	"package-action";
	public static final String PROP_PACKAGE_SERVICE = 	"package-service";
	public static final String PROP_PACKAGE_SERVICEIMPL = 	"package-service-impl";
	public static final String PROP_PACKAGE_DAO = 		"package-dao";
	public static final String PROP_PACKAGE_MODEL = 	"package-model";
	
	public static final String PROP_TEMPLATE_SERVICE 	= "template-service";
	public static final String PROP_TEMPLATE_SERVICEIMPL = "template-serviceImpl";
	public static final String PROP_TEMPLATE_ACTION	= 	"template-action";
	public static final String PROP_TEMPLATE_JSPLIST= 	"template-jsp-list";
	public static final String PROP_TEMPLATE_JSPVIEW= 	"template-jsp-view";
	
	public static final String PROP_SUFFIX_DAO = "suffix-dao";
	
	private static Properties properties;
	private static List<String> modles;
	
	
	private PropConfigure() {
		properties = new Properties();
		
	}
	
	/**
	 * 
	 */
	public static void init() {
		properties = new Properties();
		modles = new ArrayList<String>();
		try {
			SAXReader reader = new SAXReader();
	        Element root=null;
	        try {
	        	Document xmldoc = reader.read(new File(PROPERTIES_FILE));
	            root = xmldoc.getRootElement();
	            
	            Element context = root.element("context");
	            List<Element> props = context.elements();
	            for (Iterator iterator = props.iterator(); iterator.hasNext();) {
	            	Element prop = (Element) iterator.next();
					properties.put(prop.attribute("name").getValue(), prop.attribute("value").getValue());
				}
	            List<Element> elements = root.elements("modle");
	            for (Iterator iterator = elements.iterator(); iterator
						.hasNext();) {
					Element modle = (Element) iterator.next();
					modles.add(modle.attributeValue("name"));
				}
	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}catch(Exception ie) {
			throw(new ConfigureException("properties load error:" + ie.getMessage()) );
		}
	}
	
	/**
	 * @param prop
	 * @return
	 */
	public static String getProperty(String prop) {
		String value = properties.getProperty(prop);
		if(value==null)
			logger.error("Property Name ERROR:\t" + prop);
		return value;
	}
	
	public static List<String> getModleNames() {
		return modles;
	}
}
