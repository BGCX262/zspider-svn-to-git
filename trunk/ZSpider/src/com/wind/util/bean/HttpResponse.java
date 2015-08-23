package com.wind.util.bean;

public class HttpResponse
{
	private int responseCode;
	private String content;

	public HttpResponse(int responseCode, String content) {
		this.responseCode = responseCode;
		this.content = content;
	}

	public int getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(int responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "HttpResponse [responseCode=" + responseCode + ", content="
				+ content + "]";
	}
}
