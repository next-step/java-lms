package nextstep.lms.repository;

import nextstep.lms.infrastructure.JdbcLmsUserRepository;
import nextstep.lms.domain.LmsUser;
import org.junit.jupiter.api.BeforeEach;
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
public class LmsUserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LmsUserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private LmsUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcLmsUserRepository(jdbcTemplate);
    }

    @Test
    void findByUserId() {
        Optional<LmsUser> user = userRepository.findByUserId("javajigi");
        LOGGER.debug("LmsUser: {}", user.get());
        assertAll(
                () -> assertThat(user.isPresent()).isTrue(),
                () -> assertThat(user.map(LmsUser::isAdmin).orElse(false)).isTrue(),
                () -> assertThat(user.map(LmsUser::getId).orElse(0L)).isEqualTo(1L)
        );
    }

    @Test
    void save() {
        LmsUser lmsUser = LmsUser.adminOf("newUser","password","새이름");
        int count = userRepository.save(lmsUser);
        assertThat(count).isEqualTo(1);
        Optional<LmsUser> savedLmsUser = userRepository.findByUserId("newUser");
        assertAll(
                () -> assertThat(savedLmsUser.isPresent()).isTrue(),
                () -> assertThat(savedLmsUser.get().isName("새이름")).isTrue(),
                () -> assertThat(savedLmsUser.map(LmsUser::isAdmin).orElse(false)).isTrue(),
                () -> assertThat(savedLmsUser.map(LmsUser::getId).orElse(null)).isEqualTo(3L)
        );
        LOGGER.debug("LmsUser: {}", savedLmsUser);
    }
}
