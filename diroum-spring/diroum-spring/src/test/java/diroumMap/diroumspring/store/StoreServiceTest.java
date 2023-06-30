package diroumMap.diroumspring.store;

import diroumMap.diroumspring.web.repository.store.StoreRepository;
import diroumMap.diroumspring.web.domain.store.Store;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.service.store.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class StoreServiceTest {

    @MockBean
    StoreService storeService;
    @MockBean
    StoreRepository storeRepository;
    @MockBean
    EntityManager em;

    @Test
    public void storeAdd() {
        //given
        Users users = Users.builder().loginId("admin").build();
        em.persist(users);

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
        Users users = Users.builder().loginId("admin").build();
        em.persist(users);
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

