/**
 * 
 */
package com.tianyi.codegen.demo;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * FreeMarker方法类，可以编写常用方法嵌入到模板直接调用
 * @author apple
 * 
 */
public class IndexOfMethod implements TemplateMethodModel {
	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 2) {
			throw new TemplateModelException("Wrong arguments");
		}
		return new SimpleNumber(((String) args.get(1)).indexOf((String) args
				.get(0)));
	}
}