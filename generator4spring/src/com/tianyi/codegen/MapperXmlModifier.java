/**
 * 
 */
package com.tianyi.codegen;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.tianyi.util.DateUtil;

/**
 * @author apple
 * 
 */
public class MapperXmlModifier {
	private static Logger logger = Logger.getLogger(MapperXmlModifier.class);

	private String filePath;
	private String mappingXml;
	private String model;
	private String fileName;

	public MapperXmlModifier(String filePath, String mappingXml, String model) {
		super();
		this.filePath = filePath;
		this.mappingXml = mappingXml;
		this.model = model;
		this.fileName = filePath + "/" + mappingXml;
	}

	/**
	 * 修改mybatis产生的mapping Xml文件 增加Map_Where_Clause、selectByMap、countByMap等节点
	 * 
	 */
	public void modifyByMap() {
		SAXReader reader = new SAXReader();
		Element mapper = null;

		try {
			Document xmldoc = reader.read(new File(fileName));
			mapper = xmldoc.getRootElement();

			addMap_Where_Clause(xmldoc, mapper);
			addCountByMap(xmldoc, mapper);
			addSelectByMap(xmldoc, mapper);

			saveXml(xmldoc);
			logger.info("end " + filePath + "/" + mappingXml + " modifyByMap.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] ags) {
		MapperXmlModifier xmlModifier = new MapperXmlModifier(
				"gen/java/resources", "DemoMapper.xml",
				"com.tiyi.tyapp.model.Demo");
		xmlModifier.modifyByMap();
	}

	/**
	 * 增加Map_Where_Clause sql节点
	 * 
	 * @param xmldoc
	 * @param mapper
	 */
	private void addMap_Where_Clause(Document xmldoc, Element mapper) {
		mapper.addComment("\nadd by code generator,"
				+ DateUtil.format(new Date()) + "\n");

		Element sqlElement = mapper.addElement("sql");
		sqlElement.addAttribute("id", "Map_Where_Clause");
		Element whereElement = sqlElement.addElement("where");

	}

	/**
	 * 增加countByMap sql节点
	 * 
	 * @param xmldoc
	 * @param mapper
	 */
	private void addCountByMap(Document xmldoc, Element mapper) {
		mapper.addComment("\nadd by code generator,"
				+ DateUtil.format(new Date()) + "\n");
		Element selectElement = mapper.addElement("select");

		selectElement.addAttribute("id", "countByMap");
		selectElement.addAttribute("parameterType", "java.util.Map");
		selectElement.addAttribute("resultType", "java.lang.Integer");

		selectElement.addText("select count(*) from " + getTableName(mapper));

		Element ifElement = selectElement.addElement("if");
		ifElement.addAttribute("test", "_parameter != null");

		Element includeElement = ifElement.addElement("include");
		includeElement.addAttribute("refid", "Map_Where_Clause");
	}

	/**
	 *  增加selectByMap sql节点
	 * 
	 * @param xmldoc
	 * @param mapper
	 */
	private void addSelectByMap(Document xmldoc, Element mapper) {
		mapper.addComment("\nadd by code generator,"
				+ DateUtil.format(new Date()) + "\n");

		Element selectElement = mapper.addElement("select");
		selectElement.addAttribute("id", "selectByMap");
		selectElement.addAttribute("parameterType", "java.util.Map");
		selectElement.addAttribute("resultMap", "BaseResultMap");

		String selectString = "select \n"
				+ "	<include refid=\"Base_Column_List\" />\n" + "	from "
				+ getTableName(mapper);
		selectElement.addText(selectString);

		Element ifParamElement = selectElement.addElement("if");
		ifParamElement.addAttribute("test", "_parameter != null");
		Element includeElement = ifParamElement.addElement("include");
		includeElement.addAttribute("refid", "Map_Where_Clause");

		Element ifOrderElement = selectElement.addElement("if");
		ifOrderElement.addAttribute("test", "orderByClause != null");
		ifOrderElement.addText("order by ${orderByClause}");

		Element ifPageElement = selectElement.addElement("if");
		ifPageElement.addAttribute("test", "pageStart != null");
		ifPageElement.addText("limit #{pageStart,jdbcType=INTEGER}, \n"
				+ " #{pageSize,jdbcType=INTEGER}");
	}

	/**
	 * 从mapping文件insert节点中获取数据库表名称
	 * 
	 * @param xmldoc
	 * @return
	 */
	private String getTableName(Element mapper) {
		String tableName = null;
		Element insert = mapper.element("insert");
		if (insert != null) {
			String text = insert.getTextTrim();
			String[] sqls = text.split(" ");
			for (int i = 0; i < sqls.length; i++) {
				if (sqls[i].equalsIgnoreCase("into") && i < sqls.length - 2) {
					tableName = sqls[i + 1];
					break;
				}
			}
		}
		return tableName;
	}

	/**
	 * 查找节点，并返回第一个符合条件节点
	 * 
	 * @param express
	 * @param source
	 * @return
	 */
	private static Node selectSingleNode(String express, Object source) {

		return null;
	}

	/**
	 * 将Document输出到文件
	 * 
	 * @param doc
	 */
	private void saveXml(Document doc) {
		try {
			// 紧凑的格式
			// OutputFormat format = OutputFormat.createCompactFormat();
			// 排版缩进的格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 设置编码
			format.setEncoding("UTF-8");

			// 将document中的内容写入文件中 
			XMLWriter writer = new XMLWriter(new FileWriter(new File(fileName)),format);
			writer.setEscapeText(false);
			writer.write(doc);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
