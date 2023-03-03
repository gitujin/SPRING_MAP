package diroumMap.diroumspring.board;

import diroumMap.diroumspring.web.domain.Board;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.PostDto;
import diroumMap.diroumspring.web.repository.BoardRepository;
import diroumMap.diroumspring.web.service.BoardService;
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
    EntityManager em;

    @Test
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

    @Test
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
}
