package diroumMap.diroumspring.web.repository;

import diroumMap.diroumspring.web.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 검색 기능
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);
}
