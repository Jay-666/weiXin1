package com.wx.pojo;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

public class NewsMessage extends BaseMessage {
	private String ArticleCount;
	private List<News> Articles;
	
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}

	

}
