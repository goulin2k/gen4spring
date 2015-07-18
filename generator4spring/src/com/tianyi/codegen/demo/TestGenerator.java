/**
 * 
 */
package com.tianyi.codegen.demo;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tianyi.codegen.ActionGererator;
import com.tianyi.codegen.DaoMapperModifier;
import com.tianyi.codegen.GenConfiguration;
import com.tianyi.codegen.BaseCodeGenerator;
import com.tianyi.codegen.JSPGenerator;
import com.tianyi.codegen.MapperXmlModifier;
import com.tianyi.codegen.ServiceGenerator;
import com.tianyi.codegen.common.GeneratorModel;
import com.tianyi.codegen.common.Util;

import freemarker.template.Template;

/**
 * @author apple
 *
 */
public class TestGenerator {
	private static final Logger logger = Logger.getLogger(TestGenerator.class);
	private static BaseCodeGenerator gen;
	
	public static void main(String[] args) {
		logger.debug("......begin init configure...");
		
		try {
			GenConfiguration cfg = GenConfiguration.getInstance();
			List<String> modles = cfg.getGenModles();
			for (Iterator iterator = modles.iterator(); iterator.hasNext();) {
				String srcModel = (String) iterator.next();
				
				MapperXmlModifier xmlModifier = new MapperXmlModifier(filePath, mappingXml, model, namespace);
				xmlModifier.modifyByMap();
				
				DaoMapperModifier daoModifier = new DaoMapperModifier(genPath, packageName, mapper, modleClass);
				daoModifier.modifyByMap();
				
				gen = ServiceGenerator.getInstance(cfg);		
				gen.generate(srcModel);
			
				gen = ActionGererator.getInstance(cfg);
				gen.generate(srcModel);
				
				gen = JSPGenerator.getInstance(cfg);
				gen.generate(srcModel);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
