/**
 * @author 庄司世良
 */

package login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
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
		String UID = request.getParameter("UID");
		char[] password = request.getParameter("pass").toCharArray();
		HttpSession session = request.getSession();
		if(UID.substring(0, 1).equals("a")) {
			response.sendRedirect("stu_top.html");
		}
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
		Connection connection = DBManager.getConnection();
		System.out.println(connection);

//		SessionManager.creat();

	}

}
