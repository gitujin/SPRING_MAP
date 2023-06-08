package diroumMap.diroumspring.web.domain.users;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.comment.Comment;
import diroumMap.diroumspring.web.domain.like.Like;
import diroumMap.diroumspring.web.dto.user.SignUpFormDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private List<Board> board;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private List<Comment> comments;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private List<Like> like;

    @Builder
    public Users(Long id, String loginId, String password, String name, Integer age, UserRole userRole, List<Board> board, List<Comment> comments, List<Like> like) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.userRole = userRole;
        this.board = board;
        this.comments = comments;
        this.like = like;
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

