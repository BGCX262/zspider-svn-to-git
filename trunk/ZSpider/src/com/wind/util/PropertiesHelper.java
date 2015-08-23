package com.wind.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Properties文件读写工具类<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public class PropertiesHelper
{
	private static Log logger = LogFactory.getLog(PropertiesHelper.class);
	private String project_root = "";
	private File file = null;

	public PropertiesHelper(String filePath) {
		if (filePath != null && filePath.length() > 0)
		{
			try
			{
				file = new File(project_root + filePath);
			} catch (Exception e)
			{
				logger.error(e);
			}
		}
	}

	/**
	 * 获取指定key的value值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperties(String key, String charset)
	{
		InputStreamReader fis = null;
		try
		{
			Properties prop = new Properties();
			fis = new InputStreamReader(new FileInputStream(getAbsolutePath()),
					charset);
			prop.load(fis);
			return prop.getProperty(key);
		} catch (Exception e)
		{
			logger.error(e);
		} finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			} catch (Exception e)
			{
			}
		}
		return "";
	}

	/**
	 * 写入<key,value>键值对
	 * 
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void setProperties(String key, String value) throws Exception
	{
		Properties prop = new Properties();
		FileOutputStream outputFile = null;
		InputStream fis = null;
		try
		{
			// 输入流和输出流要分开处理， 放一起会造成写入时覆盖以前的属性
			fis = new FileInputStream(getAbsolutePath());
			// 先载入已经有的属性文件
			prop.load(fis);
			// 追加新的属性
			prop.setProperty(key, value);
			// 写入属性
			outputFile = new FileOutputStream(getAbsolutePath());
			prop.store(outputFile, "");

			outputFile.flush();
		} catch (Exception e)
		{
			logger.error(e);
			throw e;
		} finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			} catch (Exception e)
			{
			}
			try
			{
				if (outputFile != null)
				{
					outputFile.close();
				}
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * 
	 * @return 返回所有key，value值对
	 */
	public Map<String, String> readProperties()
	{
		Properties props = new Properties();
		Map<String, String> proResult = new HashMap<String, String>();
		try
		{
			InputStream in = new BufferedInputStream(new FileInputStream(
					getAbsolutePath()));
			props.load(in);
			Set keyValue = props.keySet();
			for (Iterator it = keyValue.iterator(); it.hasNext();)
			{
				String key = (String) it.next();
				String value = (String) props.get(key);
				proResult.put(key, value);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return proResult;
	}

	public String getAbsolutePath()
	{
		try
		{
			return file.getAbsolutePath();
		} catch (Exception e)
		{
			logger.error(e);
		}
		return "";
	}
}
