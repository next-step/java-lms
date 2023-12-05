package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUsers;
import nextstep.courses.domain.SessionUsersRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionUsersRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionUsersRepository sessionUsersRepository;

    @BeforeEach
    void setUp() {
        sessionUsersRepository = new JdbcSessionUsersRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("성공 - 강의를 등록한 유저를 조회한다.")
    void success_find_registered_users() {
        SessionUsers sessionUsers = sessionUsersRepository.findBy(1L);
        Set<NsUser> users = sessionUsers.users();

        assertThat(users).hasSize(2)
                .extracting("id")
                .containsExactly(1L, 2L);
    }
}
