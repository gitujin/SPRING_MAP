package diroumMap.diroumspring.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @Builder
    public UserDto(String loginId, String password){
        this.loginId = loginId;
        this.password = password;
    }
}
