package diroumMap.diroumspring.web.dto;

import diroumMap.diroumspring.web.domain.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String writer;
    private String comment;
    private String createdDate;
    private Long boardId;

    public Comment toEntity(Users users){
        return Comment.builder()
                .users(users)
                .comment(comment)
                .createdDate(LocalDateTime.now())
                .build();
    }

}
