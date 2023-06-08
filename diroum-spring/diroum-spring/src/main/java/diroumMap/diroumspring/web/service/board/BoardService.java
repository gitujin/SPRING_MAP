package diroumMap.diroumspring.web.service.board;

import diroumMap.diroumspring.web.domain.like.Like;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.board.BoardRepository;
import diroumMap.diroumspring.web.repository.like.MemberLikePostRepository;
import diroumMap.diroumspring.web.repository.users.UserRepository;
import diroumMap.diroumspring.web.domain.board.Board;
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
    private final MemberLikePostRepository memberLikePostRepository;

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

    /**
     * 글 좋아요
     */
    @Transactional
    public boolean saveLike(Long postId, Long userId){
        /** 로그인 한 유저가 해당 게시물을 좋아요 했는지 안했는지 확인 **/
        if(!findLike(postId, userId)) {

            /* 좋아요 하지 않은 게시물이라면 좋아요 추가, true 반환 */
            Users users = userRepository.findById(userId).orElseThrow(() ->
                    new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
            Board board = boardRepository.findById(postId).orElseThrow(() ->
                    new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

            /* 좋아요 엔티티 생성 */
            Like like = new Like(users, board);
            memberLikePostRepository.save(like);
            boardRepository.plusLike(postId);

            return true;
        } else {

            /* 좋아요 한 게시물이면 좋아요 삭제, false 반환 */
            memberLikePostRepository.deleteByBoard_IdAndUsers_Id(postId, userId);
            boardRepository.minusLike(postId);

            return false;
        }

    }

    @Transactional
    public boolean findLike(Long postId, Long userId){
        return memberLikePostRepository.existsByBoard_IdAndUsers_Id(postId, userId);
    }

}

