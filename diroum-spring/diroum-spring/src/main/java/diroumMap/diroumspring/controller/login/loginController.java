package diroumMap.diroumspring.controller.login;

import diroumMap.diroumspring.controller.SessionConst;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class loginController {

    private final LoginService loginService;

    @GetMapping("/users/login")
    public String loginForm(@ModelAttribute("loginForm") UserLoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute("loginForm") UserLoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            log.info("error = {}", bindingResult);
            return "login/loginForm";
        }

        User loginUser = loginService.login(loginForm.getLoginId(),loginForm.getPassword());

        if (loginUser == null){
            log.info("login Fail");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        // 로그인 성공
        // 세션에 로그인 회원 정보 보관
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
