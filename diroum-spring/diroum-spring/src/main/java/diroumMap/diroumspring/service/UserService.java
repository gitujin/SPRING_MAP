package diroumMap.diroumspring.service;

import diroumMap.diroumspring.Repository.UserRepository;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /*
    * 회원 가입
    */
    @Transactional
    public Long join(User user) {
        validateDuplicateLoginId(user); //중복 로그인 아이디 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateLoginId(User user) {
        userRepository.findByLoginId(user.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
        }

    /*
     * 회원 전체 조회
     */
    public HashMap<String, Object> findAll(Pageable page){
        HashMap<String, Object> listMap = new HashMap<>();
        Page<User> list = userRepository.findAll(page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());

        listMap.put("nowPage", list.getPageable().getPageNumber() + 1);
        listMap.put("startPage", Math.max(1,list.getPageable().getPageNumber() - 4));
        listMap.put("endPage", Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5));

        return listMap;
    }

    /*
    *  회원 단건 조회
    */
    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}
