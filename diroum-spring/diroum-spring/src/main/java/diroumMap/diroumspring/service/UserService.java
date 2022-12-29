package diroumMap.diroumspring.service;

import diroumMap.diroumspring.Repository.UserRepository;
import diroumMap.diroumspring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /*
    *  회원 단건 조회
    */
    public Optional<User> findOne(Long id){
        return userRepository.findOne(id);
    }

    /**
     * 회원 삭제
     */
//    public Long delete(Long id){
//        return userRepository.delete(id);
//    }
}
