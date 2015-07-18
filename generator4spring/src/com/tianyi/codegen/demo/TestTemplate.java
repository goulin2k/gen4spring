package com.tianyi.codegen.demo;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tianyi.codegen.GenConfiguration;

import freemarker.template.Template;

public class TestTemplate {
	private static Logger logger = Logger.getLogger(TestTemplate.class);
	
	private static Map<String, Object> buildSamples(String name) {
		// 创建根哈希表
		Map root = new HashMap();
		
		if(name.equalsIgnoreCase("test"))
			return buildSampleData(root);
		if(name.equalsIgnoreCase("testObject"))
			return buildSimpleObjects(root);
		
		return root;
	}
	
	/**
	 * @return
	 */
	private static Map<String, Object> buildSampleData(Map<String, Object> root) {
		
		// 在根中放入字符串"user" 
		root.put("user", "Big Joe");
		// 为"latestProduct"创建哈希表
		Map latest = new HashMap();
		// 将它添加到根哈希表中
		root.put("latestProduct", latest);
		// 在 latest 中放置"url"和"name" 
		latest.put("url", "products/greenmouse.html"); 
		latest.put("name", "green mouse");
		latest.put("date", new Date());
		latest.put("isUsed", new Boolean("true"));
		
		//放入一个方法
		root.put("indexOf", new IndexOfMethod());
		
		//放入一个指令
		root.put("upper", new UpperDirective()); 
		root.put("repeat", new RepeatDirective());
		return root;
	}
	
	private static Map<String, Object> buildSimpleObjects(Map<String, Object> root) {
		
		root.put("theString", "wombat");
		// expose an "arbitrary" java objects:
		root.put("theObject", new TestObject("green mouse", 1200));
		
		return root;
	}
	
	public static void main(String[] args) {
		logger.debug("......begin init configure...");
		
		String testName = args[0];
		if(testName == null) {
			System.out.println("please input test name...");
			return;
		}
		
		Map sample = buildSamples(testName);
		try {
		
			GenConfiguration genc = GenConfiguration.getInstance();
			/* 在整个应用的生命周期中,这个工作你可以执行多次 */
			/* 获取或创建模板*/
			Template temp = genc.getFmConfiguration().getTemplate( 
					testName + ".ftl",
					GenConfiguration.DEFAULT_ENCODING);
			Writer out = new OutputStreamWriter(System.out);
			temp.process(sample, out);
			out.flush();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return;
	}

}
