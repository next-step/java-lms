package nextstep.sessions.infrastructure;

import fixture.sessions.domain.SessionFixture;
import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionRepository sessionRepository;

	@BeforeEach
	void setUp() {
		sessionRepository = new JdbcSessionRepository(jdbcTemplate);
	}

	@Test
	void crud() {
		Session session = SessionFixture.createSessionWithEnrollment(50, SessionStatus.OPENED);
		int count = sessionRepository.save(session);
		assertThat(count).isEqualTo(1);
		Session savedSession = sessionRepository.findById(1L);
		assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());
		LOGGER.debug("Course: {}", savedSession);
	}
}
