package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.sessions.exception.AlreadyEnrollCancelException;
import nextstep.sessions.exception.EnrollCancelException;
import nextstep.sessions.type.SelectStatusType;

public class StudentTest {

	@DisplayName("수강생 생성")
	@Test
	void test1() {
		Student student = new Student(1L, 1L);
		assertThat(student).isEqualTo(new Student(1L, 1L));
	}

	@DisplayName("수강 취소 불가 - 이미 수강 취소된 수강생")
	@Test
	void test2() {
		Student student = new Student(1L, 1L, true);
		assertThatThrownBy(student::enrollCancel).isInstanceOf(AlreadyEnrollCancelException.class);
	}

	@DisplayName("수강 취소 불가 - 심사중인 수강생")
	@Test
	void test3() {
		Student student = new Student(1L, 1L);
		assertThatThrownBy(student::enrollCancel).isInstanceOf(EnrollCancelException.class);
	}

	@DisplayName("수강 취소 불가 - 선발된 수강생")
	@Test
	void test4() {
		Student student = new Student(1L, 1L);
		student.changeSelectStatus(SelectStatusType.SELECTED);
		assertThatThrownBy(student::enrollCancel).isInstanceOf(EnrollCancelException.class);
	}

	@DisplayName("선발되지 않은 수강생의 수강을 취소한다.")
	@Test
	void test5() {
		Student student = new Student(1L, 1L);
		student.changeSelectStatus(SelectStatusType.NOT_SELECTED);

		Student expected = new Student(1L, 1L, true);
		expected.changeSelectStatus(SelectStatusType.NOT_SELECTED);

		assertThat(student.enrollCancel()).isEqualTo(expected);
	}
}
