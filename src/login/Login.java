/**
 * @author 庄司世良
 */

package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SQLExecuter;
import sessionManager.sessionManager;

/**
 * Servlet implementation class Login
 */

//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Login.htmlのフォームの値を取得する
		String UID = request.getParameter("UID");
		char[] password = request.getParameter("pass").toCharArray();
//		データ
		SQLExecuter se = new SQLExecuter();
		if(se.searchRaspUser(UID,password)) {
			System.out.println("該当ユーザーを検出しました：セッションを作成");
			sessionManager sm = new sessionManager();
			sm.createSession(request,UID);
			response.sendRedirect("stu_top.html");
		}else {
			response.sendRedirect("Login.html");
		}
	}
}
