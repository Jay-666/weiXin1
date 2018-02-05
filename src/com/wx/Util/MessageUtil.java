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
	 * xmlתmap���ϵĹ�����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();//��ȡxml�ֽ�
		Document document = reader.read(ins); //
		Element root = document.getRootElement();//��ȡȫ��Ԫ��
		List<Element> list = root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());//�����Ž�map��
		}
		ins.close();
		return map;
		
	}
	
	/**
	 * ��textMessage������ݣ�ת��xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xStream = new XStream();
		String xml= xStream.toXML(textMessage);
		return xml;
		
	}
	
}
