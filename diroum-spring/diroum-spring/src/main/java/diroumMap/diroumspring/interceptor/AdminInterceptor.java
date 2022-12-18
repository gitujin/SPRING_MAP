package diroumMap.diroumspring.interceptor;

import diroumMap.diroumspring.controller.SessionConst;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {

        String requestURI = req.getRequestURI();
        HttpSession session = req.getSession();

        int userVerify = (int) session.getAttribute("LOGIN_VERIFY");
        System.out.println("userVerify = " + userVerify);

        if(userVerify != 9) {
            res.sendRedirect("/users/login?redirectURL=" + requestURI);
            return false;
        }

        log.info("관리자");
        return true; //관리자일 때
    }
}
