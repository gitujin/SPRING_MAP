package diroumMap.diroumspring.web.repository.board;

import diroumMap.diroumspring.web.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 검색 기능
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

    /* 좋아요 추가 */
    @Modifying
    @Query(value = "update board set board.like_count = board.like_count + 1 where board.board_id = :postId", nativeQuery = true)
    int plusLike(@Param("postId") Long postId);

    @Modifying
    @Query(value = "update board set board.like_count = board.like_count - 1 where board.board_id = :postId", nativeQuery = true)
    int minusLike(@Param("postId") Long postId);
}
