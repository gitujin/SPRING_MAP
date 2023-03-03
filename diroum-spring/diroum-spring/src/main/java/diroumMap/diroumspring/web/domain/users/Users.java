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

    @Builder
    public Users(String loginId, String password, String name, int age, UserRole userRole) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.userRole = userRole;
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

