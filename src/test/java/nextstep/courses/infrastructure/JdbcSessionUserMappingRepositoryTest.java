package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionUserMappingRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcSessionUserMappingRepository jdbcSessionUserMappingRepository;

    @BeforeEach
    void setup() {
        jdbcSessionUserMappingRepository = new JdbcSessionUserMappingRepository(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Test
    void save() {
        SessionUser sessionUserMapping = new SessionUser(55L, 1L, 3L, LocalDateTime.now());
        int save = jdbcSessionUserMappingRepository.save(sessionUserMapping);
        assertThat(save).isEqualTo(1);
    }

    @Test
    void findById() {
        SessionUser sessionUserMapping = jdbcSessionUserMappingRepository.findById(1L);
        assertThat(sessionUserMapping.getId()).isEqualTo(1L);
    }

    @Test
    void findBySessionId() {
        List<SessionUser> bySessionId = jdbcSessionUserMappingRepository.findBySessionId(1L);
        assertThat(bySessionId.size()).isEqualTo(5);
    }

    @Test
    void update() {
        SessionUser sessionUserMapping = jdbcSessionUserMappingRepository.findById(1L);
        sessionUserMapping.changeSessionId(2L);
        jdbcSessionUserMappingRepository.update(sessionUserMapping);

        SessionUser updateSessionUserMapping = jdbcSessionUserMappingRepository.findById(1L);
        assertThat(updateSessionUserMapping.getSessionId()).isEqualTo(2);

    }

    @Test
    void delete() {
        int delete = jdbcSessionUserMappingRepository.deleteById(1L);
        assertThat(delete).isEqualTo(1);
    }
}