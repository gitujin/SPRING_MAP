package diroumMap.diroumspring.Repository;

import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> findOne(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public Optional<User> findByLoginId(String loginId) {
        List<User> result = em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return result.stream().findAny();
    }

//    public Long delete(Long id) {
//        em.createQuery("delete from User u where u.id = id", User.class)
//                .setParameter("id", id)
//                .executeUpdate();
//        return id;
//    }
}
