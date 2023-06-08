package diroumMap.diroumspring.web.domain.store;

import diroumMap.diroumspring.web.domain.users.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeIdx")
    private Long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 45)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    private String createAt;

    @Builder
    public Store(Long id, String title, String address, String category, Users users, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.category = category;
        this.users = users;
        this.createAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createAt);
    }

    //==생성 메서드==//
    public static Store createStore(String category, String title, String address){
        return Store.builder()
                .category(category).title(title).address(address)
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Store convertEntityToDto(Store store){
        return Store.builder()
                .category(store.getCategory())
                .title(store.getTitle())
                .address(store.getAddress())
                .build();
    }

    /** 업체 수정 **/
    public void update(String category, String title, String address){
        this.category = category;
        this.title = title;
        this.address = address;
    }

}
