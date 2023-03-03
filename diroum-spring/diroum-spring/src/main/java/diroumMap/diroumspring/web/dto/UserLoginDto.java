package diroumMap.diroumspring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserLoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @Builder
    public UserLoginDto(String loginId, String password){
        this.loginId = loginId;
        this.password = password;
    }
}
