package diroumMap.diroumspring.web.service;

import diroumMap.diroumspring.web.domain.Board;
import diroumMap.diroumspring.web.domain.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.BoardRepository;
import diroumMap.diroumspring.web.repository.CommentRepository;
import diroumMap.diroumspring.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
