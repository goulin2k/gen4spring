import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.opsc.util.XMLConvert;


/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class TestXmlConverter {
	private JMSReportUserHeartBeatRequest jr;
	private static final Log _LOG = LogFactory.getLog(TestXmlConverter.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jr = new JMSReportUserHeartBeatRequest();
		jr.setUserClientIP("183.24.4.21");
		jr.setUserMAC("8783 rrer re65");
		jr.setUsername("username");
		jr.setUserType("admin");
	}

	@Test
	public void test() {
		
		XMLConvert convert = new XMLConvert();
		String xml = convert.objClazzToXml(jr);
		_LOG.info(xml);
	}
	
	@Test 
	public void test2Object() {
		String xml="<Message><UserName>username</UserName>" +
			    "<MAC>8783 rrer re65</MAC>" +
			    "<ClientIP>183.24.4.21</ClientIP>" +
			    "<UserType>admin</UserType>" +
			    "</Message>";
		
		JMSReportUserHeartBeatRequest jr = (JMSReportUserHeartBeatRequest)
				XMLConvert.xml.xmlToObjClazz(xml, JMSReportUserHeartBeatRequest.class);
		
		_LOG.info(jr);
	}

}
