package diroumMap.diroumspring.service;

import com.querydsl.core.Tuple;
import diroumMap.diroumspring.Repository.StoreInterface;
import diroumMap.diroumspring.Repository.StoreRepository;
import diroumMap.diroumspring.Repository.UserRepository;
import diroumMap.diroumspring.domain.Board;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    @Autowired
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
    public HashMap<String, Object> findAll(Pageable page){
        HashMap<String, Object> listMap = new HashMap<>();
        Page<Store> list = storeRepository.findAll(page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());

        listMap.put("nowPage", list.getPageable().getPageNumber() + 1);
        listMap.put("startPage", Math.max(1,list.getPageable().getPageNumber() - 4));
        listMap.put("endPage", Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5));

        return listMap;
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

    /**
     * 카테고리 별 업체 조회
     */
    public List<StoreInterface> findCategorized(String category){
        return storeRepository.findByCategorized(category); //카테고리 별 지점
    }
}