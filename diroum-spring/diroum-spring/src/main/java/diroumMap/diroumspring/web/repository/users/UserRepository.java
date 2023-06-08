package diroumMap.diroumspring.web.repository.users;

import diroumMap.diroumspring.web.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByLoginId(@Param("loginId") String loginId);

}