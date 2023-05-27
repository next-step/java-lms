package nextstep.users.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void 임시_데이터에_존재하는_javajigi_아이디로_조회_및_값_검증() {
        NsUser nsUser = userRepository.findByUserId("javajigi");
        assertThat(nsUser).isNotNull();

        assertThat(nsUser.getId()).isEqualTo(1L);
        assertThat(nsUser.getUserId()).isEqualTo("javajigi");
        assertThat(nsUser.getName()).isEqualTo("자바지기");
    }

    @Test
    void 임시_데이터에_존재하지_않는_아이디로_조회_실패() {
        assertThatThrownBy(() -> userRepository.findByUserId("test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않는 사용자입니다.");
    }
}
