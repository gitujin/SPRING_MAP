package diroumMap.diroumspring.service;

import diroumMap.diroumspring.Repository.BoardRepository;
import diroumMap.diroumspring.Repository.UserRepository;
import diroumMap.diroumspring.domain.Board;
import diroumMap.diroumspring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /*
    *  게시글 작성
    */
    @Transactional
    public Long register(String title, String content, Long userId){
        User user = userRepository.findById(userId).orElseThrow();

        Board board = Board.createBoard(title, content, user);
        boardRepository.save(board);
        return board.getId();
    }

    /*
    * 게시판 전체 조회
    */
    public HashMap<String, Object> findAll(Pageable page){
        HashMap<String, Object> listMap = new HashMap<>();
        Page<Board> list = boardRepository.findAll(page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());

        listMap.put("nowPage", list.getPageable().getPageNumber() + 1);
        listMap.put("startPage", Math.max(1,list.getPageable().getPageNumber() - 4));
        listMap.put("endPage", Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5));

        return listMap;
    }

    /*
    *  게시글 단건 조회
    */
    public Optional<Board> findOne(Long id){
        return boardRepository.findById(id);
    }

    /*
    * 게시글 수정
    */
    @Transactional
    public void updateBoard(Long id, String title, String content){
        Board board = boardRepository.findById(id).orElseThrow();
        board.update(title, content);
    }

    /*
    * 게시글 삭제
    */
    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    /*
     * 게시글 조회수
     */
    @Transactional
    public Board selectBoardDetail(Long id){
        Board board = boardRepository.findById(id).get();
        board.updateViewCount(board.getCount());
        return board;
    }
}

