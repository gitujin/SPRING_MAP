package diroumMap.diroumspring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignUpFormDto {

    @NotBlank(message = "아이디는 필수입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    private Integer age;

    @Builder
    public SignUpFormDto(String loginId, String password, String name, Integer age){
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
    }
}
