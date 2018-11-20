package sessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class sessionManager {
	public void createSession(HttpServletRequest request, String seki_no) {

		HttpSession session = request.getSession(false);
		if(session == null) {
			
		}else {
			Object loginCheck = session.getAttribute("login");
			if(loginCheck == null) {
				System.out.println("ログインに関するデータがありません");
			}
			session = request.getSession(true);
			session.setAttribute("login", seki_no);
			System.out.println("login"+session.getAttribute("login"));
		}
		session.removeAttribute("login");

	}

}
