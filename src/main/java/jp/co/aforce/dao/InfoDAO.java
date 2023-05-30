package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Info;

public class InfoDAO extends DAO {
	
	//searchメソッド：データベースの検索を行う
	public List<Info> search(String id, String password) throws Exception {
		List<Info> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		//IDとpasswordが一致する行を取得
		PreparedStatement st = con.prepareStatement(
				"select * from login where id = ? and password = ? ");
		st.setString(1, "%"+id+"%");
		st.setString(2, "%"+password+"%");
		ResultSet rs = st.executeQuery();
		
		//取得した行の列の値をリスト化
		while (rs.next()) {
			Info i = new Info();
			i.setId(rs.getString("id"));
			i.setPassword(rs.getString("password"));
			i.setName(rs.getString("name"));
			list.add(i);
		}
		
		st.close();
		con.close();
		
		return list;
		
	}
}
