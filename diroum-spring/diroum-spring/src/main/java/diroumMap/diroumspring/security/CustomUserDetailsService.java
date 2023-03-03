package diroumMap.diroumspring.security;

import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Users users = userRepository.findByLoginId(loginId)
                .orElseThrow(()->new UsernameNotFoundException(loginId + "는 존재하지 않는 ID 입니다."));

        return new UsersAdapter(users);
    }

}