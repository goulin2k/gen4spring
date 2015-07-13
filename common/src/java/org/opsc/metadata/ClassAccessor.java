/**
 * 
 */
package org.opsc.metadata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opsc.common.LabelStringValueBean;

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
			e.printStackTrace();
//			throw(new RuntimeException("Class Not Found Exception:\t" + cls));
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
	
	public static <T> T cast(Class<T> clazz, Object obj) {  
        return clazz.cast(obj);  
    } 
	
	
}
