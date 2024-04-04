package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.SessionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@JdbcTest
class JdbcSessionListenerRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionListenerRepository sessionListenerRepository;

    @BeforeEach
    void setUp() {
        sessionListenerRepository = new JdbcSessionListenerRepository(jdbcTemplate);
    }

    @Test
    void save() {
        int count = sessionListenerRepository.save(new SessionListener(1L, 1L));
        assertThat(count).isEqualTo(1);
    }

    @Test
    void saveAll() {
        List<SessionListener> data = List.of(
                new SessionListener(1L, 3L),
                new SessionListener(1L, 4L)
        );

        assertThatNoException()
                .isThrownBy(() -> sessionListenerRepository.saveAll(data));
    }


    @Test
    void findAllBySessionId() {
        List<SessionListener> result = sessionListenerRepository.findAllBySessionId(1L);
        assertThat(result).hasSize(2);
    }

    @Test
    void delete() {
        SessionListener data = new SessionListener(1L, 1L);
        int count = sessionListenerRepository.delete(data);
        assertThat(count).isEqualTo(1);
    }
}
