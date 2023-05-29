package nextstep.sessions.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.Students;

@JdbcTest
public class SessionRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionRepository sessionRepository;

	private StudentRepository studentRepository;

	private Session session;

	private Student student;

	@BeforeEach
	void setUp() {
		sessionRepository = new JdbcSessionRepository(jdbcTemplate);
		studentRepository = new JdbcStudentRepository(jdbcTemplate);
		session = new Session(null,1L, new SessionDate("2023-04-03T00:00:00", "2023-06-01T00:00:00"),
			"http://nextstep/coveredImageUrl.png", true, 100, new Students());
		student = new Student(1L, 1L);
	}

	@Test
	void save() {
		assertThat(sessionRepository.save(session)).isEqualTo(1);
	}

	@Test
	void findById() {
		studentRepository.save(student);
		sessionRepository.save(session);
		Session session = sessionRepository.findById(1L);
		assertThat(session).isNotNull();
		LOGGER.info(session.toString());
	}
}
