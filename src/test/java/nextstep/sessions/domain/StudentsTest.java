package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class StudentsTest {

	@DisplayName("학생 등록")
	@Test
	void test1() {
		Students students = new Students();
		students.add(NsUserTest.JAVAJIGI);

		assertThat(students).isEqualTo(new Students(List.of(NsUserTest.JAVAJIGI)));
	}

	@DisplayName("특정 학생에 대한 등록 유무 확인")
	@Test
	void test2() {
		Students students = new Students();
		students.add(NsUserTest.JAVAJIGI);

		assertThat(students.contains(NsUserTest.JAVAJIGI)).isTrue();
		assertThat(students.contains(NsUserTest.SANJIGI)).isFalse();
	}
}
