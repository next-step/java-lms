package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
public class UserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void findByUserId() {
        Optional<NsUser> nsUser = userRepository.findByUserId("javajigi");
        assertThat(nsUser.isEmpty()).isFalse();
        LOGGER.debug("NsUser: {}", nsUser.get());
    }

    @DisplayName("사용자를 저장하고 id로 조회할 수 있다.")
    @Test
    void saveAndFindById() {
        Optional<NsUser> foundUser = userRepository.findById(1L);

        assertThat(foundUser).isPresent();
        assertAll(
                () -> assertThat(foundUser.get().getUserId()).isEqualTo("javajigi"),
                () -> assertThat(foundUser.get().getPassword()).isEqualTo("test"),
                () -> assertThat(foundUser.get().getName()).isEqualTo("자바지기"),
                () -> assertThat(foundUser.get().getEmail()).isEqualTo("javajigi@slipp.net")
        );
    }
}
