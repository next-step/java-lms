package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentsTest {

	@DisplayName("학생 등록")
	@Test
	void test1() {
		Students students = new Students();
		students.add(new Student(1L, 1L));

		assertThat(students).isEqualTo(new Students(List.of(new Student(1L, 1L))));
	}

	@DisplayName("특정 학생에 대한 등록 유무 확인")
	@Test
	void test2() {
		Students students = new Students();
		students.add(new Student(1L, 1L));

		assertThat(students.contains(new Student(1L, 1L))).isTrue();
		assertThat(students.contains(new Student(1L, 2L))).isFalse();
	}
}
