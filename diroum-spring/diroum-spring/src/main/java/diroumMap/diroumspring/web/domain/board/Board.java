package diroumMap.diroumspring.web.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import diroumMap.diroumspring.web.domain.comment.Comment;
import diroumMap.diroumspring.web.domain.like.Like;
import diroumMap.diroumspring.web.domain.users.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@DynamicUpdate
@DynamicInsert
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private String registerDate;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long count;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users users;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    @JsonIgnoreProperties({"board"})
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Like> like;

    @Builder
    public Board(Long id, String title, String content, LocalDateTime registerDate, Long count, Long likeCount, Users users, List<Comment> comments, List<Like> like) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(registerDate);
        this.count = count;
        this.likeCount = likeCount;
        this.users = users;
        this.comments = comments;
        this.like = like;
    }

    /* 생성 메소드 */
    public static Board createBoard(String title, String content, Users users){
        return Board.builder()
                .title(title).content(content).users(users)
                .registerDate(LocalDateTime.now())
                .build();
    }

    /* 비즈니스 메소드 */
    /** 내용 수정 업데이트 **/
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    /** 조회수 업데이트 **/
    public Board updateViewCount(Long viewCount){
        this.count = viewCount + 1;
        return this;
    }

}
