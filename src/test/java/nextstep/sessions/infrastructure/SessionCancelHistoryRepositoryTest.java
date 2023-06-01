package nextstep.sessions.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCancelHistory;
import nextstep.sessions.domain.SessionCancelHistoryRepository;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.Sessions;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.Students;
import nextstep.users.domain.Instructor;

@JdbcTest
public class SessionCancelHistoryRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionCancelHistoryRepository sessionCancelHistoryRepository;

	@BeforeEach
	void setUp() {
		sessionCancelHistoryRepository = new JdbcSessionCancelHistoryRepository(jdbcTemplate);
	}

	@Test
	void save() {
		Student student = new Student(1L, 1L);
		Students students = new Students(List.of(student));
		Session session = new Session(1L, 1L, new SessionDate(LocalDateTime.now(), LocalDateTime.now()), "test.png", true, 100, students);
		Sessions sessions = new Sessions(List.of(session));
		Instructor instructor = new Instructor(1L, sessions);
		assertThat(sessionCancelHistoryRepository.save(new SessionCancelHistory(instructor, student))).isEqualTo(1);
	}
}
