package nextstep.session.infrastructure;

import nextstep.session.domain.StudentEnrollmentInfomation;
import nextstep.session.domain.StudentEnrollmentInfomationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentEnrollmentRepositoryTest {
	public static final StudentEnrollmentInfomation SE1 = new StudentEnrollmentInfomation(5L, 5L, 1L);
	public static final StudentEnrollmentInfomation CHANGE_SE2 = new StudentEnrollmentInfomation(1L, 1L, 3L);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private StudentEnrollmentInfomationRepository studentEnrollmentInfomationRepository;

	@BeforeEach
	void setUp() {
		studentEnrollmentInfomationRepository = new JdbcStudentEnrollmentInfomationRepository(jdbcTemplate);
	}

	@Test
	void save() {
		assertThat(studentEnrollmentInfomationRepository.save(SE1)).isEqualTo(1);
	}

	@Test
	void findAllByEnrollmentId() {
		List<StudentEnrollmentInfomation> studentEnrollmentInfomation = studentEnrollmentInfomationRepository.findAllByEnrollmentId(1L);
		assertThat(studentEnrollmentInfomation.isEmpty()).isFalse();
	}

	@Test
	void update() {
		studentEnrollmentInfomationRepository.updateByEnrollmentId(CHANGE_SE2);
		List<StudentEnrollmentInfomation> studentEnrollmentInfomation = studentEnrollmentInfomationRepository.findAllByEnrollmentId(1L);
		assertThat(studentEnrollmentInfomation.isEmpty()).isFalse();
		assertThat(studentEnrollmentInfomation.get(0).getStudentId()).isEqualTo(CHANGE_SE2.getStudentId());
	}

	@Test
	void deleteById() {
		assertThat(studentEnrollmentInfomationRepository.deleteByEnrollmentId(1L)).isEqualTo(3);
	}

}
