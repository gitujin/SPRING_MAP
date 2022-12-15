package diroumMap.diroumspring.store;

import diroumMap.diroumspring.Repository.StoreRepository;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StoreServiceTest {

    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    EntityManager em;

    @Test
    public void storeAdd() {
        //given
        User user = User.builder().loginId("admin").build();
        em.persist(user);

        //when
        Long addId = storeService.storeAdd("AAA", "BBB","CCC");

        //then
        Store store = storeRepository.findById(addId).orElseThrow();

        assertThat(store.getCategory()).isEqualTo("AAA");
        assertThat(store.getTitle()).isEqualTo("BBB");
        assertThat(store.getAddress()).isEqualTo("CCC");
    }

    @Test
    public void updateStore(){
        //given
        User user = User.builder().loginId("admin").build();
        em.persist(user);
        Long addId = storeService.storeAdd("AAA", "BBB","CCC");

        //when
        storeService.updateStore(addId, "BBB","CCC", "DDD");

        //then
        Store store = storeRepository.findById(addId).orElseThrow();

        assertThat(store.getCategory()).isEqualTo("BBB");
        assertThat(store.getTitle()).isEqualTo("CCC");
        assertThat(store.getAddress()).isEqualTo("DDD");
    }
}
