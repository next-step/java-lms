package nextstep.lms.repository;

import nextstep.lms.domain.LmsUser;
import nextstep.lms.infrastructure.JdbcLmsUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
        LmsUser user = userRepository.findByUserId("javajigi");
        LOGGER.debug("LmsUser: {}", user);
        assertAll(
                () -> assertThat(user).isNotNull(),
                () -> assertThat(user.isAdmin()).isTrue()
        );
    }

    @Test
    void save() {
        LmsUser lmsUser = LmsUser.adminOf("newUser", "password", "새이름");
        int count = userRepository.save(lmsUser);
        assertThat(count).isEqualTo(1);
        LmsUser savedLmsUser = userRepository.findByUserId("newUser");
        assertAll(
                () -> assertThat(savedLmsUser).isNotNull(),
                () -> assertThat(savedLmsUser.isName("새이름")).isTrue(),
                () -> assertThat(savedLmsUser.isAdmin()).isTrue()
        );
        LOGGER.debug("LmsUser: {}", savedLmsUser);
    }
}
