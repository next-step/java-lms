package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.record.SessionRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRespository(jdbcTemplate);
    }

    @Test
    void crud(){
        Session session = new SessionBuilder().withId(2L).build();
        int count = sessionRepository.save(session.toSessionRecord());
        assertThat(count).isEqualTo(1);

        SessionRecord sessionRecord = sessionRepository.findById(1000L);
        assertThat(sessionRecord.getMaxCapacity()).isEqualTo(100);
        assertThat(sessionRecord.getTuition()).isEqualTo(300_000L);
    }


}
