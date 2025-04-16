package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        // given
        Image image = new Image(100.0f, "png", "https://example.com/image.png", 300, 200);
        Session session = new Session(
                "도메인 주도 설계",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                10000L,
                0,
                20,
                image,
                SessionStatus.RECRUITING
        );

        // when
        int count = sessionRepository.save(session, 1L); // courseId = 1L
        assertThat(count).isEqualTo(1);

        Session saved = sessionRepository.findById(1L);

        // then
        assertThat(saved.getTitle()).isEqualTo(session.getTitle());
        assertThat(saved.getTuition()).isEqualTo(session.getTuition());
        assertThat(saved.getCapacity()).isEqualTo(session.getCapacity());
        assertThat(saved.getCoverImage().getImageUrl()).isEqualTo(session.getCoverImage().getImageUrl());

        LOGGER.debug("Session: {}", saved);
    }
}
