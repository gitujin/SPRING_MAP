package diroumMap.diroumspring.web.service;

import diroumMap.diroumspring.web.domain.Store;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.PostDto;
import diroumMap.diroumspring.web.repository.BoardRepository;
import diroumMap.diroumspring.web.repository.UserRepository;
import diroumMap.diroumspring.web.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
    *  게시글 작성
    */
    @Transactional
    public Long register(String title, String content, Long userId){
        Users users = userRepository.findById(userId).orElseThrow();

        Board board = Board.createBoard(title, content, users);
        boardRepository.save(board);
        return board.getId();
    }

    /**
    * 게시판 전체 조회
    */
    public HashMap<String, Object> findAll(Pageable page){

        HashMap<String, Object> listMap = new HashMap<>();
        Page<Board> list = boardRepository.findAll(page);
        System.out.println("list = " + list);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());

        listMap.put("nowPage", list.getPageable().getPageNumber() + 1);
        listMap.put("startPage", Math.max(1,list.getPageable().getPageNumber() - 4));
        listMap.put("endPage", Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5));

        return listMap;
    }

    /**
    *  게시글 단건 조회
    */
    public Optional<Board> findOne(Long id){
        return boardRepository.findById(id);
    }

    /**
    * 게시글 수정
    */
    @Transactional
    public void updateBoard(Long id, String title, String content){
        Board board = boardRepository.findById(id).orElseThrow();
        board.update(title, content);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    /**
     * 게시글 조회수
     */
    @Transactional
    public Board selectBoardDetail(Long id){
        Board board = boardRepository.findById(id).get();
        board.updateViewCount(board.getCount());
        return board;
    }

    /**
     * 글 검색
     */
    @Transactional
    public Page<Board> search(String keyword, Pageable pageable){
        Page<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);

        return boardList;
    }

    /**
     * 글 리스트
     */
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}

