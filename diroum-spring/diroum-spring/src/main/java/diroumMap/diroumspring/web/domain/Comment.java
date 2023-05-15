package diroumMap.diroumspring.web.domain;

import diroumMap.diroumspring.web.domain.users.Users;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "create_date")
    private String createdDate;



    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board; //게시글

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users; //작성자

    @Builder
    public Comment(String comment, Users users, Board board, LocalDateTime createdDate){
        this.comment = comment;
        this.users = users;
        this.board = board;
        this.createdDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createdDate);
    }

    // 생성 메서드
    public static Comment createComment(String comment, Users users, Board board){
        return Comment.builder()
                .comment(comment).users(users).board(board)
                .createdDate(LocalDateTime.now())
                .build();
    }

    /**
     * 비즈니스 메서드
     */
    public void updateComment(String comment){
        this.comment = comment;
    }
}
