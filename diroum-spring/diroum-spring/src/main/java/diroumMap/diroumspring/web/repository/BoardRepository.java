package diroumMap.diroumspring.web.repository;

import diroumMap.diroumspring.web.domain.Board;
import diroumMap.diroumspring.web.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
