/**
 * 
 */
package com.tianyi.codegen.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author apple
 *
 */
public class ClassAccessor {
	
	/**
	 * 根据类名获取属性 List<DisplayField>
	 * @param 	cls
	 * @return	List<DisplayField>
	 */
	public static List<DisplayField> getClassProps(String cls) {
		Class<?> obj = null; 
		List<DisplayField> list = new ArrayList<DisplayField>();
		try {
			obj = Class.forName(cls);  
			Field[] field = obj.getDeclaredFields(); 
			for (int i = 0; i < field.length; i++) {
				list.add(new DisplayField(field[i]));
				
			}
		}catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw(new GeneratException("Class Not Found Exception:\t" + cls));
		}
		return list;
	}
	
	/**
	 * 根据类名获取主键属性字段	{@link DisplayField} 
	 * @param cls
	 * @return
	 */
	public static DisplayField getKeyField(String cls) {
		List<DisplayField> list = getClassProps(cls);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DisplayField displayField = (DisplayField) iterator.next();
			if(displayField != null ) {
				if(displayField.getIsKey() == true)
					return displayField;
			}
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			List<DisplayField> list = ClassAccessor.getClassProps(
					"com.tianyi.codegen.common.GeneratorModel");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				DisplayField displayField = (DisplayField) iterator.next();
				System.out.println(displayField.getProperty() + "|" +
						displayField.getDataType());
			}
			
			DisplayField key = ClassAccessor.getKeyField("com.tianyi.codegen.common.GeneratorModel");
			System.out.println(key.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
