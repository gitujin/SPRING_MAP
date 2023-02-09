package diroumMap.diroumspring.web.controller;

import diroumMap.diroumspring.web.domain.users.User;
import diroumMap.diroumspring.web.domain.users.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {

/*  @GetMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }*/

    @GetMapping("/")
    public String loginHome(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model){

        if(loginUser == null){
            return "home";
        }

        if(loginUser.getUserRole() == UserRole.ROLE_ADMIN){
            model.addAttribute("user", loginUser);
            return "adminHome";
        }

        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}
