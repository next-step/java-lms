package nextstep.users.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

//    @Test
//    void crud() {
//        NsUser nsUser = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
//        int count = userRepository.save(nsUser);
//        assertThat(count).isEqualTo(1);
//        NsUser savedUser = userRepository.findByUserId("jerry").orElseThrow();
//        assertThat(savedUser.getUserId()).isEqualTo(nsUser.getUserId());
//        assertThat(savedUser.getName()).isEqualTo(nsUser.getName());
//        assertThat(savedUser.getEmail()).isEqualTo(nsUser.getEmail());
//        assertThat(savedUser.getPassword()).isEqualTo(nsUser.getPassword());
//        LOGGER.debug("NsUser: {}", savedUser);
//    }
}
