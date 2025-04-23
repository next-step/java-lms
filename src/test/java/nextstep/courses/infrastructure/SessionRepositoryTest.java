package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    SessionPeriod period;
    NsImage image;
    Price price;
    Session paidSession;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(5);
        period = new SessionPeriod(start, end);
        image = new NsImage(500_000, "image/png", 300, 200);
        price = Price.of(1000L);
        paidSession = Session.createPaid(2L, period, List.of(image), 1, price);
    }

    @Test
    void crud() {
        int savedId = sessionRepository.save(paidSession);
        assertThat(savedId).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(paidSession.getMeta()).isEqualTo(savedSession.getMeta());
        assertThat(paidSession.isFree()).isEqualTo(savedSession.isFree());
        LOGGER.debug("Session: {}", savedSession);
    }
}
