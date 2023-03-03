package diroumMap.diroumspring.web.controller.home;

import diroumMap.diroumspring.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/users/home")
    public String loginPage(HttpServletRequest req){
        return "loginHome";
    }

    @GetMapping("/admin/home")
    public String adminPage(){
        return "adminHome";
    }

}