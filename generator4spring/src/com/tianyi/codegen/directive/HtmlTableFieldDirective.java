/**
 * 
 */
package com.tianyi.codegen.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import com.tianyi.codegen.common.DisplayField;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author apple
 * 
 */
public class HtmlTableFieldDirective implements TemplateDirectiveModel {
	private static final String PARAM_NAME_FIELDNAME = "name";
	private static final String PARAM_NAME_FIELDTYPE = "dataType";
	private static final String PARAM_NAME_ISKEY = "isKey";

	/**
	 * 
	 */
	public HtmlTableFieldDirective() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		DisplayField displayField = null;
		Iterator paramIter = params.entrySet().iterator();

		while (paramIter.hasNext()) {
			Map.Entry ent = (Map.Entry) paramIter.next();
			String paramName = (String) ent.getKey();
			TemplateModel paramValue = (TemplateModel) ent.getValue();

			if (paramName.equals(PARAM_NAME_FIELDNAME)) {
				if (!(paramValue instanceof DisplayField)) {
					throw new TemplateModelException("The \""
							+ PARAM_NAME_FIELDNAME + "\" parameter "
							+ "must be a DisplayField.");
				}
				displayField = (DisplayField) paramValue;
			} else {
				throw new TemplateModelException("Unsupported parameter: "
						+ paramName);
			}
		}

		if (displayField == null) {
			throw new TemplateModelException("The required \""
					+ PARAM_NAME_FIELDNAME + "\" paramter" + "is missing.");
		}

		body.render(new HtmlTDFilterWriter(env.getOut()));

	}
	
	/**
	 * A {@link Writer} that transforms the character stream to upper case and
	 * forwards it to another {@link Writer}.
	 */
	private static class HtmlTDFilterWriter extends Writer {

		private final Writer out;

		HtmlTDFilterWriter(Writer out) {
			this.out = out;
		}

		public void write(char[] cbuf, int off, int len) throws IOException {
			out.write("<td>html td</td>");
		}

		public void flush() throws IOException {
			out.flush();
		}

		public void close() throws IOException {
			out.close();
		}
	}


}

