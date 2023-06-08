package diroumMap.diroumspring.web.controller.user.login;

import diroumMap.diroumspring.web.dto.user.UserDto;
import diroumMap.diroumspring.web.service.users.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/users/login") //로그인 버튼 누르면 로그인 창으로
    public String loginForm(@ModelAttribute("loginForm") UserDto userDto,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login/loginForm";
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
