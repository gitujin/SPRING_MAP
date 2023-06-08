package diroumMap.diroumspring.web.repository.comment;

import diroumMap.diroumspring.web.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId(Long id);
    void deleteById(Long Id);

}
