package com.wind.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.wind.util.bean.HttpResponse;

/**
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-02
 */
public final class HttpUtil
{
	/**
	 * POST方式
	 */
	public static final String METHOD_POST = "POST";

	/**
	 * GET方式
	 */
	public static final String METHOD_GET = "GET";

	private HttpUtil() {
	}

	/**
	 * 获得指定网址内容
	 * 
	 * @param urlStr
	 *            url地址
	 * @param paramStr
	 *            传的参数
	 * @param method
	 *            请求方法 POST GET
	 * @param outCharset
	 *            输出数据流编码
	 * @param inCharset
	 *            输入数据流编码
	 * @param requestProperties
	 *            请求属性
	 * @return http响应结果
	 * @throws IOException
	 */
	public static HttpResponse getContent(String urlStr, String paramStr,
			String method, String outCharset, String inCharset,
			Properties requestProperties, String ip, int port)
			throws IOException
	{
		if (StringUtils.isBlank(urlStr))
			return null;

		if (!(METHOD_GET.equalsIgnoreCase(method) || METHOD_POST
				.equalsIgnoreCase(method)))
		{
			throw new IllegalArgumentException(
					"param method must be one of POST or GET");
		}

		if (outCharset == null || !Charset.isSupported(outCharset))
		{
			throw new IllegalArgumentException(
					"param outCharset is not Supported");
		}

		if (inCharset == null || !Charset.isSupported(inCharset))
		{
			throw new IllegalArgumentException(
					"param inCharset is not Supported");
		}

		// System.out.println("urlStr: "+urlStr+"?"+paramStr);

		// 获得连接输出流
		HttpURLConnection conn = null;
		BufferedReader reader = null;

		OutputStreamWriter osw = null;
		OutputStream os = null;
		InputStream is = null;
		try
		{

			// get 方法要吧参数加到后面
			if (METHOD_GET.equalsIgnoreCase(method)
					&& !StringUtils.isBlank(paramStr)
					&& urlStr.indexOf('?') == -1)
			{
				urlStr += "?" + paramStr;
			}

			URL url = new URL(urlStr);

			if (ip.equals("") || ip == null)
			{
				conn = (HttpURLConnection) url.openConnection();
			} else
			{
				Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(ip,
						port));
				conn = (HttpURLConnection) url.openConnection(proxy);
			}

			conn.setConnectTimeout(20000);
			conn.setReadTimeout(20000);
			conn.setRequestMethod(method.toUpperCase());
			conn.setDoInput(true);
			conn.setUseCaches(false);

			// 设置请求属性
			if (!(requestProperties == null || requestProperties.isEmpty()))
			{
				Enumeration enumeration = requestProperties.propertyNames();
				while (enumeration.hasMoreElements())
				{
					String key = (String) enumeration.nextElement();
					conn.setRequestProperty(key,
							requestProperties.getProperty(key));
				}
			}

			// post 提交参数
			if (METHOD_POST.equalsIgnoreCase(method)
					&& !StringUtils.isBlank(paramStr))
			{
				conn.setDoOutput(true);

				// 输出
				os = conn.getOutputStream();

				osw = new OutputStreamWriter(os, outCharset);
				osw.write(paramStr);
				osw.close();
				os.close();
				os = null;
			}

			String content = null;
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				// 输入
				is = conn.getInputStream();
				reader = new BufferedReader(
						new InputStreamReader(is, inCharset));

				content = IOUtils.toString(reader);
				is = null;
			}

			HttpResponse httpResponse = new HttpResponse(
					conn.getResponseCode(), content);
			return httpResponse;
		} finally
		{
			if (reader != null)
				reader.close();

			if (osw != null)
			{
				osw.close();
			}

			if (os != null)
				os.close();

			if (is != null)
				is.close();

			if (conn != null)
			{
				conn.disconnect();
			}
		}
	}

	/**
	 * 获得ip地址
	 * 
	 * @param request
	 *            用户请求
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		if (request == null)
			return null;

		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = "";
		}
		if (ip != null && ip.indexOf(",") != -1 && ip.length() > 6)
		{
			ip = ip.split(",")[0];
		}
		return ip;
	}
}
