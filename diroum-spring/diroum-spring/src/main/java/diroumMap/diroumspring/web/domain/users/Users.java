package diroumMap.diroumspring.web.domain.users;

import diroumMap.diroumspring.web.dto.SignUpFormDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;
    private String password;
    private String name;
    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String provider; //oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId; // oauth2를 이용할 경우 아이디 값

    @Builder
    public Users(String loginId, String password, String name, int age, UserRole userRole) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.userRole = userRole;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Users(String loginId, UserRole userrole, String provider, String providerId){
        this.loginId = loginId;
        this.userRole = userrole;
        this.provider = provider;
        this.providerId = providerId;
    }


    public static Users createUser(SignUpFormDto signUpUserDto, PasswordEncoder passwordEncoder) {
        Users users = Users.builder()
                .loginId(signUpUserDto.getLoginId())
                .password(passwordEncoder.encode(signUpUserDto.getPassword()))
                .name(signUpUserDto.getName())
                .age(signUpUserDto.getAge())
                .userRole(UserRole.ROLE_USER)
                .build();
        return users;
    }

}

