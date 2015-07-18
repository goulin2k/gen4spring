/**
 * 
 */
package com.tianyi.codegen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.tianyi.codegen.common.Util;
import com.tianyi.util.DateUtil;

/**
 * @author apple
 * 
 */
public class DaoMapperModifier {
	private String genPath;
	private String packageName;
	private String mapperClass;
	private String mapperFile;
	private String modleClass;

	private static Logger logger = Logger.getLogger(DaoMapperModifier.class);

	/**
	 * @param genPath
	 * @param packageName
	 * @param mapper
	 */
	public DaoMapperModifier(String genPath, String packageName, 
			String mapper,String modleClass) {
		super();
		this.genPath = genPath;
		this.packageName = packageName;
		this.mapperClass = mapper;
		this.modleClass = modleClass;
		this.mapperFile = genPath + "/" + Util.packageToPath(packageName) + "/"
				+ mapper + ".java";
	}

	public void modifyByMap() {
		logger.info("begin modify mapping java file: " + mapperFile);
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(mapperFile));
			String data = reader.readLine();// 一次读入一行，直到读入null为文件结束
			while (data != null) {
				contents.append(data + "\n");
				if(data.indexOf("updateByPrimaryKey(") > 0 ) {
					contents.append("\n");
					contents.append("\t/** add by code generator	" + DateUtil.format(new Date()) + "*/\n");
					contents.append("\tpublic int countByMap(Map<String, Object> map);" + "\n");
					contents.append("\tpublic List<" + modleClass + "> selectByMap(Map<String, Object> map);" + "\n");
				}
				data = reader.readLine(); // 接着读下一行
			}
			reader.close();
			logger.info("modified file content:\n" + contents.toString());
			
			writer  = new BufferedWriter(new FileWriter(mapperFile,false));
			writer.write(contents.toString());
			writer.flush();
			writer.close();
			logger.info("End modify java mapper file:" + mapperFile);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally {
			
		}
	}

	/**
	 * 测试程序
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DaoMapperModifier modifier = new DaoMapperModifier("gen/java",
				"com.tiyi.tyapp.dao", "DemoMapper", "Demo");
		modifier.modifyByMap();
	}

}
