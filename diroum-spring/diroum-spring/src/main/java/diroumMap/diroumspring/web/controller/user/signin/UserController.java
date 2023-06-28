package diroumMap.diroumspring.web.controller.user.signin;

import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.user.SignUpFormDto;
import diroumMap.diroumspring.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String signupForm(@ModelAttribute("signUpFormDto") SignUpFormDto signUpFormDto) {
        log.info("signupForm");
        return "users/signupForm";
    }

    @PostMapping("/join")
    public String signup(@Valid @ModelAttribute SignUpFormDto signUpFormDto, BindingResult bindingResult, Model model) {

        log.info("signup");

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "users/signupForm";
        }

        try {
            Users users = Users.createUser(signUpFormDto, passwordEncoder);
            userService.join(users);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "users/signupForm";
        }

        log.info("signup success");
        return "redirect:/login/loginForm";
    }

}
