package diroumMap.diroumspring.interceptor;

import diroumMap.diroumspring.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {

        String requestURI = req.getRequestURI();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        if(user == null || user.getVerify() != 9) { //비회원이거나 관리자가 아닐 때
            res.sendRedirect("/users/login?redirectURL=" + requestURI);
            return false;
        }
        return true; //관리자일 때
    }
}
