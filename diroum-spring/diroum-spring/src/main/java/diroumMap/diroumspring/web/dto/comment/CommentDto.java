package diroumMap.diroumspring.web.dto.comment;

import diroumMap.diroumspring.web.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String writer;
    private String contents;
    private String createdDate;
    private Long boardId;

    public CommentDto(Long id, String writer, String contents, String createdDate, Long boardId) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.boardId = boardId;
    }

    public static CommentDto commentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getUsers().getLoginId(),
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getBoard().getId()
        );
    }
}



