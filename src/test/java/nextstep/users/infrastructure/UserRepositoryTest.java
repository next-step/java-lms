package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
public class UserRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @DisplayName("유저 저장")
    @Test
    void testSave() {
        NsUser nsUser = new NsUser("test-user", "password", "name", "test@naver.com");
        assertDoesNotThrow(() -> userRepository.save(nsUser));
    }

    @DisplayName("유저 아이디로 조회")
    @Test
    void findByUserId() {
        NsUser nsUser = new NsUser("1", "password", "javajigi", "javajigi@slipp.net", "강사");
        userRepository.save(nsUser);
        assertThat(userRepository.findByUserId("1")).isNotNull();
    }

    @DisplayName("여러 유저 아이디로 조회")
    @Test
    void findUsersByIds() {
        NsUser nsUser1 = new NsUser("1", "password", "user1", "user1@example.com", "강사");
        NsUser nsUser2 = new NsUser("2", "password", "user2", "user2@example.com", "강사");
        NsUser nsUser3 = new NsUser("3", "password", "user3", "user3@example.com", "강사");

        userRepository.save(nsUser1);
        userRepository.save(nsUser2);
        userRepository.save(nsUser3);

        List<String> userIds = Arrays.asList("1", "2", "3");

        List<NsUser> users = userRepository.findByUserIds(userIds);

        assertThat(users).hasSize(3);
    }
}
