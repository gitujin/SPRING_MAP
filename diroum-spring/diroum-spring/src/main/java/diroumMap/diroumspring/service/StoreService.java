package diroumMap.diroumspring.service;

import diroumMap.diroumspring.Repository.StoreRepository;
import diroumMap.diroumspring.Repository.UserRepository;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    /**
     * 업체 추가
     */
    @Transactional
    public Long storeAdd(String category, String title, String address){

        Store store = Store.createStore(category, title, address);
        storeRepository.save(store);
        return store.getId();
    }

    /**
     * 업체 전체 조회
     */
    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    /**
     * 업체 단건 조회
     */
    public Optional<Store> findOne(Long id){
        return storeRepository.findById(id);
    }

    /**
     * 업체 수정
     */
    @Transactional
    public void updateStore(Long id, String category, String title,  String address){
        Store store = storeRepository.findById(id).orElseThrow();
        store.update(category, title, address);
    }

    /**
     * 업체 삭제
     */
    @Transactional
    public void deleteById(Long id){
        storeRepository.deleteById(id);
    }
}
