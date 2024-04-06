package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcUserRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcUserRepository jdbcUserRepository;

    @BeforeEach
    void setUp() {
        jdbcUserRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void findByUserId() {
        Optional<NsUser> nsUser = jdbcUserRepository.findByUserId("javajigi");
        assertThat(nsUser.isPresent()).isTrue();
        assertThat(nsUser.isEmpty()).isFalse();
    }

    @Test
    void findById() {
        Optional<NsUser> nsUser = jdbcUserRepository.findById(1L);
        assertThat(nsUser.isEmpty()).isFalse();
        assertThat(nsUser.isPresent()).isTrue();
    }

    @Test
    void findByIds() {
        List<Long> ids = List.of(1L, 2L);
        List<NsUser> nsUsers = jdbcUserRepository.findByIds(ids);

        assertThat(nsUsers)
                .hasSize(2)
                .extracting("userId", "name")
                .containsExactly(Tuple.tuple("javajigi", "자바지기"), Tuple.tuple("sanjigi", "산지기"));
    }

    @Test
    void save() {
        NsUser nsUser = new NsUser(0L, "ljw1126", "1234!@#$", "ljw", "github.com/ljw1126");
        assertThat(jdbcUserRepository.save(nsUser)).isEqualTo(3L);
    }

    @DisplayName("수정할 경우 name, email 변경할 수 있다")
    @Test
    void update() {
        Optional<NsUser> optionalNsUser = jdbcUserRepository.findById(1L);
        NsUser target = new NsUser(1L, "javajigi", "test", "자바지기2", "javajigi@slipp.net");

        NsUser nsUser = optionalNsUser.get();
        nsUser.update(nsUser, target);

        int count = jdbcUserRepository.update(nsUser);
        assertThat(count).isEqualTo(1);
    }
}
