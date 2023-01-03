package diroumMap.diroumspring.Repository;

import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.loginId = :loginId")
    Optional<User> findByLoginId(@Param("loginId") String loginId);
}