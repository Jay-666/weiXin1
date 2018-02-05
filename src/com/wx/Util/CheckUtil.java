package com.wx.Util;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
	
	private static final String token = "nzj";
	/**
	 * ��token��signatrue��nonce������ַ�������sBuffer
	 * ��ϣ�㷨����sBuffer��Ȼ����signature�Ƚ�
	 * @param signatrue
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignatrue(String signature, String timestamp, String nonce){
		String [] arr = new String[]{token,timestamp,nonce};
		//����
		Arrays.sort(arr);
		//ת���ֽ�
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < arr.length; i++){
			sBuffer.append(arr[i]);
		}
		String temp = getSha1(sBuffer.toString());
		return signature.equals(temp);
		
	}
	
	
	/**
	 * 	SHA����
		���ߣ� ����_ 
		���ӣ�http://www.imooc.com/article/4257
		��Դ��Ľ����
	 * @param str
	 * @return
	 */
	public static String getSha1(String str ) {
		if (str == null || str.length() == 0) {
		return null ;
		}
		char hexDigits [] = { '0' , '1' , '2' , '3' , '4', '5' , '6' , '7' , '8' , '9' ,
		'a', 'b', 'c', 'd', 'e', 'f' };
		       try {
		           MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
		            mdTemp.update( str.getBytes( "UTF-8"));

		            byte[] md = mdTemp .digest();
		            int j = md .length ;
		            char buf [] = new char[ j * 2];
		            int k = 0;
		            for (int i = 0; i < j ; i ++) {
		                 byte byte0 = md [i ];
		                 buf[ k++] = hexDigits[byte0 >>> 4 & 0xf];
		                 buf[ k++] = hexDigits[byte0 & 0xf];
		           }
		            return new String(buf );
		      } catch (Exception e ) {
		            return null ;
		      }
		  }

//		���ߣ� ����_ 
//		���ӣ�http://www.imooc.com/article/4257
//		��Դ��Ľ����
	
}
