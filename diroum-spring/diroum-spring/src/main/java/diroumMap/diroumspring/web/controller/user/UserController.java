package diroumMap.diroumspring.web.controller.user;

import diroumMap.diroumspring.web.domain.users.User;
import diroumMap.diroumspring.web.dto.SignUpFormDto;
import diroumMap.diroumspring.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

/*    @Autowired
    PasswordEncoder passwordEncoder;*/

    @GetMapping("/users/join")
    public String signupForm(@ModelAttribute SignUpFormDto signUpUserDto) {
        log.info("signupForm");
        return "users/signupForm";
    }

    @PostMapping("/users/join")
    public String signup(@Valid @ModelAttribute SignUpFormDto signUpUserDto, BindingResult bindingResult, Model model) {

        log.info("signup");

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "users/signupForm";
        }

        try {
            User user = User.createUser(signUpUserDto, passwordEncoder);
            userService.join(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "users/signupForm";
        }

        log.info("signup success");
        return "redirect:/";
    }

}
