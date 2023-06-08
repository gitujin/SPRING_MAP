package diroumMap.diroumspring.web.domain.comment;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.users.Users;
import lombok.*;

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
    private String content;

    @Column(name = "create_date")
    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board; //게시글

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users; //작성자

    @Builder
    public Comment(Long id, String content, LocalDateTime createdDate, Board board, Users users) {
        this.id = id;
        this.content = content;
        this.createdDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createdDate);
        this.board = board;
        this.users = users;
    }

    /** 생성 메소드 **/
    public static Comment createComment(String content, Users users, Board board){
        return Comment.builder()
                .content(content).users(users).board(board)
                .createdDate(LocalDateTime.now())
                .build();
    }

    /** 댓글 수정 업데이트 **/
    public void updateComment(String content){
        this.content = content;
    }
}
