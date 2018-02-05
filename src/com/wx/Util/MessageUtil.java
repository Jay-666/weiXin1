package com.wx.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMComment;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wx.pojo.TextMessage;



public class MessageUtil {

	/**
	 * xml转map集合的工具类
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();//获取xml字节
		Document document = reader.read(ins); //
		Element root = document.getRootElement();//获取全部元素
		List<Element> list = root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());//遍历放进map中
		}
		ins.close();
		return map;
		
	}
	
	/**
	 * 将textMessage类的内容，转成xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xStream = new XStream();
		String xml= xStream.toXML(textMessage);
		return xml;
		
	}
	
}
