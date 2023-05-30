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
import nextstep.sessions.exception.ProgressStatusException;
import nextstep.sessions.type.ProgressStatusType;
import nextstep.sessions.type.RecruitStatusType;

public class EnrollmentTest {

	private Student student;

	@BeforeEach
	void setUp() {
		student = new Student(1L, 1L);
	}

	@DisplayName("최대 수강인원 예외 케이스 - 음수")
	@Test
	void test1() {
		assertThatThrownBy(() -> new Enrollment(ProgressStatusType.PREPARING, RecruitStatusType.RECRUITING, -1, new Students())).isInstanceOf(CapacityNumberException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 모집중이 아님")
	@Test
	void test2() {
		Enrollment enrollment = new Enrollment(ProgressStatusType.IN_PROGRESS, RecruitStatusType.NOT_RECRUITING, 100, new Students());
		assertThatThrownBy(() -> enrollment.enroll(student)).isInstanceOf(NotRecruitingException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 준비중이거나 이미 종료된 강의")
	@EnumSource(value = ProgressStatusType.class, names = {"PREPARING", "TERMINATION"})
	@ParameterizedTest
	void test3(ProgressStatusType input) {
		Enrollment enrollment = new Enrollment(input, RecruitStatusType.RECRUITING, 100, new Students());
		assertThatThrownBy(() -> enrollment.enroll(student)).isInstanceOf(ProgressStatusException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 정원이 가득참")
	@Test
	void test4() {
		Enrollment enrollment = new Enrollment(ProgressStatusType.IN_PROGRESS, RecruitStatusType.RECRUITING, 1, new Students(new ArrayList<>(List.of(student))));
		assertThatThrownBy(() -> enrollment.enroll(new Student(1L, 2L))).isInstanceOf(NumberFullException.class);
	}

	@DisplayName("수강 신청 예외 케이스 - 동일 계정으로 이미 신청함")
	@Test
	void test5() {
		Enrollment enrollment = new Enrollment(ProgressStatusType.IN_PROGRESS, RecruitStatusType.RECRUITING, 2, new Students(new ArrayList<>(List.of(student))));
		assertThatThrownBy(() -> enrollment.enroll(student)).isInstanceOf(AlreadySignUpException.class);
	}

	@DisplayName("수강 신청을 한다.")
	@Test
	void test6() {
		Enrollment enrollment = new Enrollment(ProgressStatusType.IN_PROGRESS, RecruitStatusType.RECRUITING, 2, new Students(new ArrayList<>(List.of(student))));
		assertThat(enrollment.enroll(new Student(1L, 2L))).isEqualTo(new Student(1L, 2L));
	}
}
