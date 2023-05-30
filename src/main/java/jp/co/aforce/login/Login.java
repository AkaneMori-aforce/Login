package jp.co.aforce.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Info;
import jp.co.aforce.dao.InfoDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/jp.co.aforce.login/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			request.setCharacterEncoding("UTF-8");
			//login.jspからidとpasswordを受け取る
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			InfoDAO dao = new InfoDAO();
			List<Info> list = dao.search(id, password);
			
			//リストが空でないとき、リストのnameのデータを返す
			/*？jspに遷移すると文字化けする
			 *？正しくないid/passwordを入れてもリストが空にならず、
			 *  if(list != null)の実行がされてしまう
			 */
			if(list != null) {
				for(Info i : list) {
					//？nullになってしまう
					request.setAttribute("name", i.getName());
				}
				request.getRequestDispatcher("/jsp/login-success.jsp").forward(request, response);
			}else {
				request.setAttribute("failure", "※IDもしくはパスワードが違います");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

}
