package diroumMap.diroumspring.web.service.comment;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.comment.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.comment.CommentDto;
import diroumMap.diroumspring.web.repository.board.BoardRepository;
import diroumMap.diroumspring.web.repository.comment.CommentRepository;
import diroumMap.diroumspring.web.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 댓글 작성하기
     */
    @Transactional
    public void writeComment(String comments, Long userId, Long boardId){

        Users users = userRepository.findById(userId).orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow();

        Comment comment = Comment.createComment(comments, users, board);
        commentRepository.save(comment);

        board.getComments().add(comment);
    }

    /**
     *  댓글 리스트
     */
    public List<Comment> commentsList(Long postId){
        return commentRepository.findByBoardId(postId);
    }

    /**
     * 댓글 삭제
     */
    public String deleteComment(Long id){
        commentRepository.deleteById(id);
        return "home";
    }

    /**
     * 댓글 수정
     */
    public void updateComment(Long id, String comments){
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.updateComment(comments);
    }

}