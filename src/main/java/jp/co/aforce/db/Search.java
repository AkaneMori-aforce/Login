package jp.co.aforce.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.co.aforce.tool.Page;

/**
 * Servlet implementation class search
 */
@WebServlet("/Login/jp.co.aforce.db/search")
public class Search extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Page.header(out);
		
		try {
			InitialContext ic = new InitialContext();
			DataSource ds =(DataSource)ic.lookup(
					"java:/comp/env/jdbc/login_db");
			Connection con = ds.getConnection();
			
			PreparedStatement st = con.prepareStatement(
					"select * from login");
			ResultSet rs = st.executeQuery();
			
			out.println("ID：パスワード：名前<br>");
			
			while (rs.next()) {
				out.println(rs.getString("id"));
				out.println("：");
				out.println(rs.getString("password"));
				out.println("：");
				out.println(rs.getString("name"));
				out.println("<br>");
			}
			
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		
		Page.footer(out);
	}
}
