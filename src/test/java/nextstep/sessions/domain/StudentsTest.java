package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.sessions.exception.CapacityNumberException;
import nextstep.users.domain.NsUserTest;

public class StudentsTest {

	@DisplayName("수강 인원 예외 케이스 - 음수")
	@Test
	void test1() {
		assertThatThrownBy(() -> new Students(-1)).isInstanceOf(CapacityNumberException.class);
	}

	@DisplayName("학생 등록")
	@Test
	void test2() {
		Students students = new Students(100);
		students.add(NsUserTest.JAVAJIGI);

		assertThat(students).isEqualTo(new Students(100, List.of(NsUserTest.JAVAJIGI)));
	}

	@DisplayName("특정 학생에 대한 등록 유무 확인")
	@Test
	void test3() {
		Students students = new Students(100);
		students.add(NsUserTest.JAVAJIGI);

		assertThat(students.contains(NsUserTest.JAVAJIGI)).isTrue();
		assertThat(students.contains(NsUserTest.SANJIGI)).isFalse();
	}
}
