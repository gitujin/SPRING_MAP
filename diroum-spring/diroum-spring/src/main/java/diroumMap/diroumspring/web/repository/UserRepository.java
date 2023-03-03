package diroumMap.diroumspring.web.repository;

import diroumMap.diroumspring.web.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    //@Query("select u from User u where u.loginId = :loginId")
    Optional<Users> findByLoginId(@Param("loginId") String loginId);

/*    @Query("select u.loginId from User u where u.userId = : userId")
    Users findById(@Param("userId") String userId);*/
}