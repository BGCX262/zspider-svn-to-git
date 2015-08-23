package com.wind.spider.core.dao.impl;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.wind.spider.core.dao.DbHandle;

/**
 * 以db4o对象数据库存储抓取的数据
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-03
 * 
 */
public class DbHandleBydb4o implements DbHandle
{
	private String dbFilePath;
	private ObjectContainer db;

	public DbHandleBydb4o() {
		super();
	}

	public DbHandleBydb4o(String dbFilePath) {
		this.dbFilePath = dbFilePath;
	}

	@SuppressWarnings("deprecation")
	public void OpenDb()
	{
		db = Db4o.openFile(dbFilePath);
	}

	public synchronized void insert(Object object)
	{
		if (db != null)
		{
			try
			{
				db.store(object);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public boolean CloseDb()
	{
		return db.close();
	}

	public String getDbFilePath()
	{
		return dbFilePath;
	}

	public void setDbFilePath(String dbFilePath)
	{
		this.dbFilePath = dbFilePath;
	}
}
