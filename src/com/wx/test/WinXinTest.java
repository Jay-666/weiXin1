package com.wx.test;

import com.wx.Util.WinXinUtil;
import com.wx.pojo.AccessToken;

public class WinXinTest {

	public static void main(String[] args) {
		AccessToken token = WinXinUtil.geAccessToken();
		System.out.println("access_token:"+token.getToken());
		System.out.println("ecpires_in:"+token.getExpiresIn());
	}

}
