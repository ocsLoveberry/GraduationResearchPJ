package stuPunchSearch;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SQLExecuter;

/**
 * Servlet implementation class StuPunchSearch
 */
@WebServlet("/StuPunchSearch")
public class StuPunchSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuPunchSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formDate = request.getParameter("formDate");
		String formClassroom = request.getParameter("classroom");
		HttpSession session = request.getSession();
		String seki_no  = (String)session.getAttribute("seki_no");
		SQLExecuter se = new SQLExecuter();

		List<String[]> resultStuPunch = se.searchStuPunch(seki_no, formDate, formClassroom);
//		list<[SEKI_NO],[ENTRY_DATE],[LOBE_ID]>
//		取り出し方 resultStuPunch.get(int index【range 0 ~】)[int index【range 0 ~ 2】]
//		ex)resultStuPunch.get(0)[0]
//		解説)listの0番目に入ってるStringの配列の0番目の要素を取り出す
//		System.out.println(resultStuPunch.get(0)[1]);
//		System.out.println(resultStuPunch.get(0)[2]);
		request.setAttribute("resultStuPunch", resultStuPunch);
		getServletConfig().getServletContext().getRequestDispatcher("/stu_punch_search_result.jsp").forward(request, response);
	}

}
