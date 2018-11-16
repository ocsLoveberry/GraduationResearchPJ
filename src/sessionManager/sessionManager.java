package sessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class sessionManager {
	public void createSession(HttpServletRequest request, String seki_no) {
	    HttpSession session = request.getSession();
	    session.setAttribute("seki_no", seki_no);
	    System.out.println("session確立中:"+session.getAttribute("seki_no"));
	}

}
