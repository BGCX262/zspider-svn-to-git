package com.wind.util;

import java.io.File;

/**
 * 目录文件工具类<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public class FileUtil
{
	/**
	 * 创建目录
	 * 
	 * @param path
	 *            路径
	 */
	public static void createDirectory(String path)
	{
		File fp = new File(path);
		// 创建目录
		if (!fp.exists())
		{
			fp.mkdirs();// 目录不存在的情况下，创建目录。
		}
	}

	/**
	 * 取得文件名后缀
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static String getSuffixes(String path)
	{
		int suffixesStart = path.indexOf(".");
		return path.substring(suffixesStart);
	}

	public static void main(String[] args)
	{
		// createDirectory("D:/kuxun/B");
		System.out.println(getSuffixes("D:/kuxun/b.properties"));
	}
}
