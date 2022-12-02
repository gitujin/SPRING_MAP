package diroumMap.diroumspring.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    private Integer age;
}
