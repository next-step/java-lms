package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.CoverImage;
import nextstep.sessions.domain.PriceType;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.utils.TestImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024 * 1024, "jpeg"));
        PriceType priceType = PriceType.PAID;
        Integer price = 10000;
        Integer maxAttendees = 10;

        Session session = new Session(0L, title, startAt, endAt, coverImage, priceType, price, maxAttendees);

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(0L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        LOGGER.debug("Session: {}", savedSession);
    }
}
