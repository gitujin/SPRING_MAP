package diroumMap.diroumspring.controller.login;

import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String login(@Valid @ModelAttribute("loginForm") UserLoginForm loginForm, BindingResult bindingResult){

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

        return "redirect:/";
    }
}
