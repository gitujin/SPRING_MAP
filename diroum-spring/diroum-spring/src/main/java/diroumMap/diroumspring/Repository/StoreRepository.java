package diroumMap.diroumspring.Repository;

import diroumMap.diroumspring.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "select s.title, s.address, s.category from Store as s where s.category = :category", nativeQuery = true)
    List<StoreInterface> findByCategorized(@Param("category") String category); //category별 지점.
}