package sessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class sessionManager {
	public void createSession(HttpServletRequest request, String seki_no) {

//		セッションが存在しないか？
		HttpSession session = request.getSession();
		session.setAttribute("seki_no", seki_no);
		System.out.println("sessionは"+session.getAttribute("seki_no")+"やで");

	}

}
