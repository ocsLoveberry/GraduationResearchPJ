package filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,ServletException{
        System.out.println("ログインチェック");

        // セッションが存在しない場合NULLを返す
        HttpSession session = ((HttpServletRequest)req).getSession(false);

        if(session != null){
            // セッションがNULLでなければ、通常どおりの遷移
            chain.doFilter(req, res);
        }else{
            // セッションがNullならば、ログイン画面へ飛ばす
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html");
            dispatcher.forward(req,res);
        }

    }

    public void init(FilterConfig config) throws ServletException{}
    public void destroy(){}
}