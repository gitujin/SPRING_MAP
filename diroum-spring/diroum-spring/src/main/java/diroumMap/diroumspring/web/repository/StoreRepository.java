package diroumMap.diroumspring.web.repository;

import diroumMap.diroumspring.web.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "select s.title, s.address, s.category from Store as s where s.category = :category", nativeQuery = true)
    List<StoreInterface> findByCategorized(@Param("category") String category); //category별 지점.

    // 검색기능 1
    Page<Store> findByTitleContaining(String keyword, Pageable pageable);
}