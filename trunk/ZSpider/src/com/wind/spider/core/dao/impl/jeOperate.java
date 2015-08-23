package com.wind.spider.core.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * 封装对Berkely DB的操作<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-03
 * 
 */
public abstract class jeOperate
{
	private Environment env;
	private static final String CLASS_CATALOG = "java_class_catelog";
	protected StoredClassCatalog javaCatelog;
	protected Database catelogdatabase;
	protected Database database;

	public jeOperate(String homeDirectory) throws DatabaseException,
			FileNotFoundException {
		System.out.println("Opening environment in:" + homeDirectory);
		// 打开env
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);
		env = new Environment(new File(homeDirectory), envConfig);
		// 设置DatabaseConfig
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true);
		dbConfig.setAllowCreate(true);
		// 打开
		catelogdatabase = env.openDatabase(null, CLASS_CATALOG, dbConfig);
		javaCatelog = new StoredClassCatalog(catelogdatabase);
		// 设置DatabaseConfig
		DatabaseConfig dbConfig0 = new DatabaseConfig();
		dbConfig0.setTransactional(true);
		dbConfig0.setAllowCreate(true);
		// 打开
		database = env.openDatabase(null, "URL", dbConfig0);
	}

	/**
	 * 关闭数据库，关闭环境
	 * 
	 * @throws DatabaseException
	 */
	public void close() throws DatabaseException
	{
		database.close();
		javaCatelog.close();
		env.close();
	}

	// put 方法
	protected abstract void put(Object key, Object value);

	// get 方法
	protected abstract Object get(Object key);

	// delete 方法
	protected abstract Object delete(Object key);
}
