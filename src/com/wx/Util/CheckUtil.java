package com.wx.Util;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
	
	private static final String token = "nzj";
	/**
	 * 把token，signatrue，nonce排序成字符串数组sBuffer
	 * 哈希算法加密sBuffer，然后与signature比较
	 * @param signatrue
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignatrue(String signature, String timestamp, String nonce){
		String [] arr = new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//转成字节
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < arr.length; i++){
			sBuffer.append(arr[i]);
		}
		String temp = getSha1(sBuffer.toString());
		return signature.equals(temp);
		
	}
	
	
	/**
	 * 	SHA加密
		作者： 鬼球_ 
		链接：http://www.imooc.com/article/4257
		来源：慕课网
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

//		作者： 鬼球_ 
//		链接：http://www.imooc.com/article/4257
//		来源：慕课网
	
}
