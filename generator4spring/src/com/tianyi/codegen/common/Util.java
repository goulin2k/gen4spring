/**
 * 
 */
package com.tianyi.codegen.common;

import java.io.File;

/**
 * @author apple
 *
 */
public class Util {
	
	/**
	 * 将类名转换为变量名（首字母小写）
	 * @param className
	 * @return
	 */
	public static String classNameToVarName(String className) {
		if(className == null)
			return null;
		if(className.length() ==0)
			return null;
		String first = className.substring(0,1);
		String firstUper = first.toLowerCase();
		
		return className.replaceFirst(first, firstUper);
		
	}
	
	/**
	 * @param fullClassName
	 * @return
	 */
	public static String getPackgeName(String fullClassName) {
		if(fullClassName == null)
			return null;
		if(fullClassName.length() ==0)
			return null;
		
		int ptLast = fullClassName.lastIndexOf('.'); 
		if(ptLast < 0)
			return null;
		
		return fullClassName.substring(0, ptLast);
	}
	
	/**
	 * @param fullClassName
	 * @return
	 */
	public static String getClassName(String fullClassName) {
		if(fullClassName == null)
			return null;
		if(fullClassName.length() ==0)
			return null;
		
		int ptLast = fullClassName.lastIndexOf('.'); 
		if(ptLast < 0)
			return null;
		
		return fullClassName.substring(ptLast + 1);
	}
	
	/**
	 * 把包名转换为目录文件夹字符串（相对路径，例如com/tianyi/gen)
	 * @param packageName
	 * @return
	 */
	public static String packageToPath(String packageName) {
		
		if(packageName == null)
			return null;
		return packageName.replace(".", "/");
	}
	
	
	/**
	 * @param dirPath
	 */
	public static void mkdir(String dirPath) {
		
		File dir = new File(dirPath); // 建立代表目录的File对象，并得到它的一个引用   
        if (dir.exists()) { // 检查目录是否存在   
            //dir.delete();    
        } else {   
            dir.mkdirs(); // 建立包目录  
            System.out.println("创建包目录：\t" + dir.getAbsolutePath());
        } 
        
	}
	

}
