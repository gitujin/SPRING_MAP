package diroumMap.diroumspring.web.domain.like;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.comment.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Like(Long id, Users users, Board board) {
        this.id = id;
        this.users = users;
        this.board = board;
    }

    /** Like 엔티티 생성 **/
    @Builder
    public Like(Users users, Board board){
        this.users = users;
        this.board = board;
    }

}
