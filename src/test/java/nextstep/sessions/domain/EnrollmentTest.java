package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.type.StatusType;

public class EnrollmentTest {

	private Student student;

	@BeforeEach
	void setUp() {
		student = new Student(1L, 1L);
	}

	@DisplayName("최대 수강인원 예외 케이스 - 음수")
	@Test
	void test1() {
		assertThatThrownBy(() -> new Enrollment(StatusType.PREPARING, -1, new Students())).isInstanceOf(CapacityNumberException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 모집중이 아님")
	@EnumSource(value = StatusType.class, names = {"PREPARING", "TERMINATION"})
	@ParameterizedTest
	void test2(StatusType input) {
		Enrollment enrollment = new Enrollment(input, 100, new Students());
		assertThatThrownBy(() -> enrollment.enroll(student)).isInstanceOf(NotRecruitingException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 정원이 가득참")
	@Test
	void test3() {
		Enrollment enrollment = new Enrollment(StatusType.RECRUITING, 1, new Students(new ArrayList<>(List.of(student))));
		assertThatThrownBy(() -> enrollment.enroll(new Student(1L, 2L))).isInstanceOf(NumberFullException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 동일 계정으로 이미 신청함")
	@Test
	void test4() {
		Enrollment enrollment = new Enrollment(StatusType.RECRUITING, 2, new Students(new ArrayList<>(List.of(student))));
		assertThatThrownBy(() -> enrollment.enroll(student)).isInstanceOf(AlreadySignUpException.class);
	}

	@DisplayName("수강 신청을 한다.")
	@Test
	void test5() {
		Enrollment enrollment = new Enrollment(StatusType.RECRUITING, 2, new Students(new ArrayList<>(List.of(student))));
		assertThat(enrollment.enroll(new Student(1L, 2L))).isEqualTo(new Student(1L, 2L));
	}
}
