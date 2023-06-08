package diroumMap.diroumspring.board;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.board.BoardRepository;
import diroumMap.diroumspring.web.repository.comment.CommentRepository;
import diroumMap.diroumspring.web.service.board.BoardService;
import diroumMap.diroumspring.web.service.comment.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("글 작성 테스트")
    public void register(){
        //given
        Users users = Users.builder().loginId("userA").build();
        em.persist(users);

        //when
        Long registerId = boardService.register("AAA","BBB", users.getId());

        //then
        Board board = boardRepository.findById(registerId).orElseThrow();

        assertThat(board.getTitle()).isEqualTo("AAA");
        assertThat(board.getContent()).isEqualTo("BBB");
        assertThat(board.getUsers()).isEqualTo(users);

    }

//    @Test
//    @DisplayName("댓글 작성 테스트")
//    public void registerComments(){
//        //given
//        Users users = Users.builder().loginId("userA").build();
//        em.persist(users);
//        Long registerId = boardService.register("AAA","BBB", users.getId());
//        Board board = boardRepository.findById(registerId).orElseThrow();
//
//        //when
//        Long writeComment = commentService.writeComment("AAA", users.getId(), board.getId());
//
//        //then
//        Comment comment = commentRepository.findById(writeComment).orElseThrow();
//
//        assertThat(comment.getComment()).isEqualTo("AAA");
//        assertThat(comment.getUsers()).isEqualTo(users);
//        assertThat(comment.getBoard()).isEqualTo(board);
//
//    }

    @Test
    @DisplayName("글 수정 테스트")
    public void updateBoard() {
        //given
        Users users = Users.builder().loginId("userA").build();
        em.persist(users);
        Long registerId = boardService.register("AAA", "BBB", users.getId());

        //when
        boardService.updateBoard(registerId, "CCC", "DDD");

        //then
        Board board = boardRepository.findById(registerId).orElseThrow();

        assertThat(board.getTitle()).isEqualTo("CCC");
        assertThat(board.getContent()).isEqualTo("DDD");
        assertThat(board.getUsers()).isEqualTo(users);
    }

    @Test
    @DisplayName("글 삭제 테스트")
    public void deleteBoard(){
        //given
        Users users = Users.builder().loginId("userA").build();
        em.persist(users);
        Long registerId = boardService.register("AAA","BBB", users.getId());

        //when
        boardService.deleteById(registerId);

        //then
        Board board = boardRepository.findById(registerId).orElseThrow();


    }
}
