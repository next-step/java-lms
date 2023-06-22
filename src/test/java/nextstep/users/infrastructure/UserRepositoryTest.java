package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class UserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Test
    void findByUserId() {
        Optional<NsUser> nsUser = userRepository.findByUserId("sanjigi");
        assertThat(nsUser.isEmpty()).isFalse();
        LOGGER.debug("NsUser: {}", nsUser.get());
    }

    @Test
    void findByUsers() {
        List<Long> ids = new ArrayList<>();
        for (int i = 1001; i < 1116; i++) {
            ids.add((long) i);
        }

        List<NsUser> users = userRepository.findByIds(ids);
        assertThat(users.size()).isEqualTo(115);
        LOGGER.debug("NsUser: {}", users);
    }
}
