package diroumMap.diroumspring.member;

import diroumMap.diroumspring.config.WebSecurityConfig;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UsersServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    @Test
    void 회원가입(){
        //given
        Users users = Users.builder().loginId("userA").build();

        //when
        Long joinId = userService.join(users);

        //then
        assertThat(users).isEqualTo(userService.findOne(joinId).orElseThrow());
    }

    @Test
    @DisplayName("패스워드 암호화 테스트")
    void encodeTest() {
        // given
        String rawPW = "1234";

        // when
        String encodePW = webSecurityConfig.passwordEncoder().encode(rawPW);

        // then
        assertThat(rawPW).isNotEqualTo(encodePW);
    }

    @Test
    @DisplayName("패스워드 일치 테스트")
    void matchTest() {
        // given
        String rawPW = "1234";
        String encodePW = webSecurityConfig.passwordEncoder().encode(rawPW);
        String inputPW = "1234";

        // when
        Boolean check = webSecurityConfig.passwordEncoder().matches(inputPW, encodePW);

        // then
        assertThat(check).isEqualTo(true);
    }

    @Test
    @DisplayName("패스워드 불일치 테스트")
    void notMatchTest() {
        // given
        String originalPW = "1234"; // 설정한 PW의 원본
        String inputPW = "123456"; // 입력한 PW의 원본
        String encodePW = webSecurityConfig.passwordEncoder().encode(originalPW); // 설정한 PW를 인코딩한 값

        // when
        Boolean check = webSecurityConfig.passwordEncoder().matches(inputPW, encodePW);
        // 입력한 PW와 설정한 PW를 인코딩한 값을 matches()를 통해 비교한다.
        // originalPW와 inputPW가 다르기 때문에 false 나와야 함.

        // then
        assertThat(check).isEqualTo(false);
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Users users1 = Users.builder().loginId("userA").build();
        Users users2 = Users.builder().loginId("userA").build();

        //when
        userService.join(users1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(users2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

}
