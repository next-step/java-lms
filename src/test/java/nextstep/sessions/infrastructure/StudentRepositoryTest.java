package nextstep.sessions.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;

@JdbcTest
public class StudentRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private StudentRepository studentRepository;

	@BeforeEach
	void setUp() {
		studentRepository = new JdbcStudentRepository(jdbcTemplate);
	}

	@Test
	void save() {
		assertThat(studentRepository.save(new Student(1L, 1L))).isEqualTo(1);
	}

	@Test
	void findBySessionId() {
		studentRepository.save(new Student(1L, 1L));
		studentRepository.save(new Student(1L, 2L));

		List<Student> students = studentRepository.findBySessionId(1L);

		assertThat(students).hasSize(2);
		assertThat(students).containsExactly(new Student(1L, 1L), new Student(1L, 2L));
	}

	@Test
	void findBySessionIdAndNsUserId() {
		studentRepository.save(new Student(1L, 1L));
		Student student = studentRepository.findBySessionIdAndNsUserId(1L, 1L);
		assertThat(student).isEqualTo(new Student(1L, 1L));
	}
}
