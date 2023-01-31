package diroumMap.diroumspring.web.controller;

import diroumMap.diroumspring.web.domain.User;
import diroumMap.diroumspring.web.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/users/join")
    public String signupForm(@ModelAttribute UserForm userForm) {
        log.info("signupForm");
        return "users/signupForm";
    }

    @PostMapping("/users/join")
    public String signup(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult) {

        log.info("signup");

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "users/signupForm";
        }

        User user = User.builder()
                .loginId(userForm.getLoginId())
                .password(userForm.getPassword())
                .name(userForm.getName())
                .age(userForm.getAge()).build();

        userService.join(user);

        log.info("signup success");
        return "redirect:/";
    }

}
