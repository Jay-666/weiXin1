package com.wx.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DefaultCaret;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMComment;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wx.pojo.News;
import com.wx.pojo.NewsMessage;
import com.wx.pojo.TextMessage;

import net.sf.json.JSONObject;



public class MessageUtil {
	
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_NEWS="news";
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
		sb.append("2.���ںŽ���\n\n");
		sb.append("3.�����ӵı���\n\n");
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
	

	/**
	 * ƴ��һ��text��Ϣ������xml��ʽ
	 * @param FromUserName
	 * @param ToUserName
	 * @param Content
	 * @return
	 */
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
	
	//newsmessageתxml
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new News().getClass());
		String xml= xStream.toXML(newsMessage);
		return xml;		
	}
	
	/**
	 * ͼ����Ϣ��ƴ��
	 * @param FromUserName
	 * @param ToUserName
	 * @return
	 */
	public static String initNewsMessage(String FromUserName,String ToUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		News news  = new News();
		news.setTitle("�����ӵ��ճ�");
		news.setDescription("��������ʵ��Leo");
		news.setPicUrl("http://nzj18.free.ngrok.cc/weiXin1/image/Leo.jpg");
		news.setUrl("www.szcat.org");
		
		newsList.add(news);
		newsMessage.setFromUserName(ToUserName);
		newsMessage.setToUserName(FromUserName);
		newsMessage.setCreateTime(new Date().toString());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount("1");
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	
	
}
