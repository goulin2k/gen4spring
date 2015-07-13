/**
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved. 
 * This software is the confidential and proprietary information of Founder. 
 * You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 */


import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opsc.common.BaseObject;


/**
 * 天翼协议消息服务上报用户心跳[用户下线]的请求参数
 * 
 * @author mendy
 * @like yin85@163.com
 * @date 2014-1-9 上午11:33:28
 * 
 *       <pre>
 *  <Message>
 *      <UserName>登录账号</UserName>
 *      <MAC>电脑马克</MAC>
 *      </Message>
 * </pre>
 * 
 *       <pre>
 * <Message>
<UserName>登录账号</UserName>
<MAC>登录客户端MAC</MAC>
<ClientIP>登录客户端IP</ClientIP>
<UserType>用户类型(CU/MCU)</UserType>
</Message>
 * </pre>
 */
@XmlRootElement(name = "Message")
@XmlAccessorType(XmlAccessType.NONE)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlType(name = "", propOrder = { "username", "userMAC", "userClientIP", "userType" })
public class JMSReportUserHeartBeatRequest extends BaseObject{

	@XmlElement(name = "UserName")
	private String username; // 登录账号
	
	@XmlElement(name = "MAC")
	private String userMAC; // 登录客户端MAC
	
	@XmlElement(name = "ClientIP")
	private String userClientIP; // 登录客户端IP
	
	@XmlElement(name = "UserType")
	private String userType; // 用户类型(CU/MCU)

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserMAC() {
		return userMAC;
	}

	public void setUserMAC(String userMAC) {
		this.userMAC = userMAC;
	}

	public String getUserClientIP() {
		return userClientIP;
	}

	public void setUserClientIP(String userClientIP) {
		this.userClientIP = userClientIP;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	// @XmlElement(name = "UserName", required = true)
	// private String userName; // 登录账号
	// @XmlElement(name = "MAC", required = true)
	// private String mac; // 用户登录的mac地址
	//
	// public String getUserName() {
	// return userName;
	// }
	//
	// public void setUserName(String userName) {
	// this.userName = userName;
	// }
	//
	// public String getMac() {
	// return mac;
	// }
	//
	// public void setMac(String mac) {
	// this.mac = mac;
	// }

}
