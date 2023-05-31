package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.Sessions;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.Students;
import nextstep.sessions.type.SelectStatusType;
import nextstep.users.exception.NotOwnedSessionException;
import nextstep.users.exception.NotStudentOfSessionException;

public class InstructorTest {

	private Student student;
	private Session session;
	private Sessions sessions;

	@BeforeEach
	void setUp() {
		this.student = new Student(1L, 1L, false, SelectStatusType.NOT_SELECTED);
		this.session = new Session(1L, 1L, new SessionDate("2023-04-03T00:00:00", "2023-06-01T00:00:00"), "http://nextstep/coveredImageUrl.png", true, 100, new Students(List.of(this.student)));
		this.sessions = new Sessions(List.of(session));
	}

	@DisplayName("수강 취소 불가 - 강사의 강의가 아님")
	@Test
	void test1() {
		Instructor instructor = new Instructor(1L, sessions);
		Session anotherSession = new Session(2L, 1L, new SessionDate("2023-04-03T00:00:00", "2023-06-01T00:00:00"), "http://nextstep/coveredImageUrl.png", true, 100, new Students());
		assertThatThrownBy(() -> instructor.enrollCancel(anotherSession, student)).isInstanceOf(NotOwnedSessionException.class);
	}

	@DisplayName("수강 취소 불가 - 강사의 강의가 아님")
	@Test
	void test2() {
		Instructor instructor = new Instructor(1L, sessions);
		Student anotherStudent = new Student(2L, 1L);
		assertThatThrownBy(() -> instructor.enrollCancel(session, anotherStudent)).isInstanceOf(NotStudentOfSessionException.class);
	}

	@DisplayName("강사가 수강생의 수강을 취소한다.")
	@Test
	void test3() {
		Instructor instructor = new Instructor(1L, sessions);
		assertThat(instructor.enrollCancel(session, student)).isEqualTo(new Student(1L, 1L, true, SelectStatusType.NOT_SELECTED));
	}
}
