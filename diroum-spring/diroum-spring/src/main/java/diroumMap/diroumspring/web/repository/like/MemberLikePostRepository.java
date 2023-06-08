package diroumMap.diroumspring.web.repository.like;

import diroumMap.diroumspring.web.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLikePostRepository extends JpaRepository<Like, Long> {

    /** 유저가 특정 게시물을 좋아요 했는지 확인 **/
    boolean existsByBoard_IdAndUsers_Id(@Param("postId") Long postId, @Param("userId")Long userId);

    /** 좋아요 삭제 **/
    void deleteByBoard_IdAndUsers_Id(@Param("postId")Long postId, @Param("userId")Long userId);
}
