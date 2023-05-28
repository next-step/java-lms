package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {

	@DisplayName("수강생 생성")
	@Test
	void test1() {
		Student student = new Student(1L, 1L);
		assertThat(student).isEqualTo(new Student(1L, 1L));
	}
}
