package diroumMap.diroumspring.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private User user;

    private String createAt;

    @Builder
    public Store(String title, String address, String category, LocalDateTime createAt){
        this.title = title;
        this.address = address;
        this.category = category;
        this.createAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createAt);
    }

    //==생성 메서드==//
    public static Store createStore(String category, String title, String address){
        return Store.builder()
                .category(category).title(title).address(address)
                .createAt(LocalDateTime.now())
                .build();
    }

    //==비즈니스 메서드==//
    public void update(String category, String title,String address){
        this.category = category;
        this.title = title;
        this.address = address;
    }
}
