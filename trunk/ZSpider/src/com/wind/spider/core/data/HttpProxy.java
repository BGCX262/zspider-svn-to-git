package com.wind.spider.core.data;

/**
 * 代理地址
 * 
 * @author yanjun.zhou
 * 
 */
public class HttpProxy
{
	private String ip;
	private Integer port;

	public HttpProxy() {
	}

	public HttpProxy(String ip, Integer port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Integer getPort()
	{
		return port;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}
}
