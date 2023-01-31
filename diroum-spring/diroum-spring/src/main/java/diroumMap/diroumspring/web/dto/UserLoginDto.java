package diroumMap.diroumspring.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserLoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
