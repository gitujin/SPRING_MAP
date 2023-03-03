package diroumMap.diroumspring.web.service;

import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    /**
    * 로그인
    * @return null 로그인 실패
    */
    public Users login(String loginId, String password){
        return userRepository.findByLoginId(loginId)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

}
