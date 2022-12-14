package diroumMap.diroumspring.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;
    private String password;
    private String name;
    private Integer age;

    @Column(columnDefinition = "integer default 0")
    private int verify;

    @Builder
    public User(String loginId, String password, String name, int age, int verify) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.verify = verify;
    }
}


