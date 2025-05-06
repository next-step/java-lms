package nextstep.courses.infrastructure;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.metadata.Period;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("세션 저장 후 조회")
    void crud() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        Session session = Session.createPaidSession(1L, period, null, Amount.of(10_000), 3);


        int result = sessionRepository.save(session);

        Assertions.assertEquals(1, result);
        Session found = sessionRepository.findById(1L);
        // org.assertj.core.api.Assertions.assertThat(found.getId()).isEqualTo(1L);
    }


}
