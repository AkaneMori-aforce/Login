package jp.co.aforce.dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	//DataSourceを保存する変数
	//static：DataSourceを一つだけ持てるように
	static DataSource ds;
	
	public Connection getConnection() throws Exception {
		//DataSourceの初期化
		if(ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:/comp/env/jdbc/login_db");
		}
		return ds.getConnection();
	}
	
}
