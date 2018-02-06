package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.dom4j.DocumentException;

import com.wx.Util.CheckUtil;
import com.wx.Util.MessageUtil;
import com.wx.pojo.TextMessage;

/**
 * Servlet implementation class wxServlet
 */
@WebServlet("/wxServlet")
public class wxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 验证是否能接入接口
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String signature = (String) request.getParameter("signature");
		String timestamp = (String) request.getParameter("timestamp");
		String nonce  = (String) request.getParameter("nonce");
		String echostr  = (String) request.getParameter("echostr");
		System.out.println(signature+"|"+timestamp+"|"+nonce+"|"+echostr);
		PrintWriter out = response.getWriter();
		if(CheckUtil.checkSignatrue(signature, timestamp, nonce)){
			out.print(echostr);
		}
		out.close();
	}

	/**
	 * 微信接收发送信息用post
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();//获取out要在设置编码之后，不然也会中文乱码
		try {
			Map map = MessageUtil.xmlToMap(request);
			String ToUserName= (String) map.get("ToUserName");
		    String FromUserName= (String) map.get("FromUserName");
			String CreateTime= (String) map.get("CreateTime");
			String MsgType= (String) map.get("MsgType");
			String Content= (String) map.get("Content");
			String MsgId= (String) map.get("MsgId");
			String message = null;
			if(MsgType.equals(MessageUtil.MESSAGE_TEXT)){
				if(Content.equals("1")){
					message = MessageUtil.initText(FromUserName, ToUserName, MessageUtil.firstText());
				}else if(Content.equals("2")){
					message = MessageUtil.initText(FromUserName, ToUserName, MessageUtil.secondText());
				}else if(Content.equals("?") || Content.equals("？") ){
					message = MessageUtil.initText(FromUserName, ToUserName, MessageUtil.menuText());
				}
				
			}else if(MsgType.equals(MessageUtil.MESSAGE_EVENT)){
				String eventType = (String) map.get("Event");
				if(eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)){//关注时
					message = MessageUtil.initText(FromUserName, ToUserName, MessageUtil.menuText());
				}
			}
			System.out.println(message);
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}

}
