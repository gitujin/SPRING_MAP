package diroumMap.diroumspring.web.controller.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserLoginForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
