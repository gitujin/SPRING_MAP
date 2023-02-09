package diroumMap.diroumspring.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diroumMap.diroumspring.web.domain.users.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String registerDate;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long count;

    @Builder
    public Board(String title, String content, User user, LocalDateTime registerDate, Long count) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.count = count;
        this.registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(registerDate);
    }

    // 생성 메서드
    public static Board createBoard(String title, String content, User user){
        return Board.builder()
                .title(title).content(content).user(user)
                .registerDate(LocalDateTime.now())
                .build();
    }

    // 비즈니스 메서드
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Board updateViewCount(Long viewCount){
        this.count = viewCount + 1;
        return this;
    }

}
