package diroumMap.diroumspring.web.service.users;

import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
    * 회원 가입
    */
    @Transactional
    public Long join(Users users) {
        validateDuplicateLoginId(users); //중복 로그인 아이디 검증
        userRepository.save(users);
        return users.getId();
    }

    /**
     * 중복 로그인 아이디 검증
     */
    private void validateDuplicateLoginId(Users users) {
        userRepository.findByLoginId(users.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
        }

    /**
     * 회원 전체 조회
     */
    public HashMap<String, Object> findAll(Pageable page){
        HashMap<String, Object> listMap = new HashMap<>();
        Page<Users> list = userRepository.findAll(page);

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
     *  회원 단건 조회
     */
    public Optional<Users> findOne(Long id){
        return userRepository.findById(id);
    }

    /**
     *  회원 아이디 조회
     */
    public Optional<Users> findByLoginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}
