package com.wx.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
	
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";

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
		xStream.alias("xml", textMessage.getClass());
		String xml= xStream.toXML(textMessage);
		return xml;
		
	}
	
	
	/**
	 * ��עʱ�����˵�
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ��Ĺ�ע������԰��ղ˵���ʾ������\n\n");
		sb.append("1.���˽���\n");
		sb.append("1.���ںŽ���\n\n");
		sb.append("�ظ�'?'�����˲˵���");
		return sb.toString();
	}
	//�ظ���1��
	public static String firstText(){
		StringBuffer sb = new StringBuffer();
		sb.append("�У�21���˳�����С���� ");
		return sb.toString();
	}
	//�ظ���2��
	public static String secondText(){
		StringBuffer sb = new StringBuffer();
		sb.append("�����ںŴ��ڲ��Խ׶�");
		return sb.toString();
	}
	

	
	public static String initText(String FromUserName,String ToUserName,String Content){
		TextMessage text = new TextMessage();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setCreateTime(new Date().toString());
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setContent(Content);
		String message=MessageUtil.textMessageToXml(text);
		return message;
	}
	
}
