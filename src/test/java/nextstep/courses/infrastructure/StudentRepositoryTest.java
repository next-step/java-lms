package nextstep.courses.infrastructure;

import nextstep.courses.builder.PaidSessionBuilder;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class StudentRepositoryTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private StudentRepository studentRepository;

	@BeforeEach
	void setUp() {
		studentRepository = new JdbcStudentRepository(jdbcTemplate);
	}

	@Test
	void 등록_성공() {
		assertThat(studentRepository.save(
				new Student(NsUserTest.JAVAJIGI,
				new PaidSessionBuilder().build()))).isEqualTo(1);
	}
}
