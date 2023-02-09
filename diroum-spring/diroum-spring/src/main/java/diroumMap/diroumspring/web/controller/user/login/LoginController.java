package diroumMap.diroumspring.web.controller.user.login;

import diroumMap.diroumspring.web.controller.SessionConst;
import diroumMap.diroumspring.web.domain.users.User;
import diroumMap.diroumspring.web.dto.UserLoginDto;
import diroumMap.diroumspring.web.domain.users.UserRole;
import diroumMap.diroumspring.web.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/users/login") //로그인 버튼 누르면 로그인 창으로
    public String loginForm(@ModelAttribute("loginForm") UserLoginDto userLoginDto){
        return "login/loginForm";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute("loginForm") UserLoginDto userLoginDto,
                        BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL){

        if(bindingResult.hasErrors()){
            log.info("error = {}", bindingResult);
            return "login/loginForm";
        }

        User loginUser = loginService.login(userLoginDto.getLoginId(),userLoginDto.getPassword());
        System.out.println("loginUser = " + loginUser);

        if (loginUser == null){
            log.info("login Fail");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";

        }

        UserRole verify = loginUser.getUserRole();
        System.out.println("verify = " + verify);

        // 로그인 성공
        // 세션에 로그인 회원 정보 보관
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        session.setAttribute("LOGIN_VERIFY", verify);


        return "redirect:" + redirectURL;
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
