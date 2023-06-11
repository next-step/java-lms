package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.exception.CannotEnrollException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudentTest {

    Student june1;
    Student june2;
    Student june3;
    Session session;

    @BeforeEach
    void setUp() {
        june1 = new Student(10L, 0L);
        june2 = new Student(10L, 1L);
        june3 = new Student(10L, 2L);
        session = SessionCreator.create(2L, SessionStatus.OPENED);
    }

    @Test
    @DisplayName("학생은 수강신청시 강의의 현재 수강인원이 1이 증가한다.")
    void enroll_SessionMaxNumberOfValue_PlusOne() {
        june1.enroll(session);
        assertThat(session.totalStudentNum()).isEqualTo(1);
    }

    @Test
    @DisplayName("수강신청시 최대 수강신청 인원을 넘을 경우 예외를 던진다.")
    void enroll_OutOfMaxNumberOfStudent_ThrowException() {
        session.enroll(june1);
        session.enroll(june2);

        assertThatThrownBy(() -> session.enroll(june3))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다.");
    }

    @Test
    @DisplayName("수강신청시 최대 수강인원을 넘지 않을 경우 예외를 던지지 않는다.")
    void enroll_LessThanMaxNumberOfStudent_NoException() {
        Session session = SessionCreator.create(3L, SessionStatus.OPENED);

        assertThatNoException().isThrownBy(() -> session.enroll(june1));
        assertThatNoException().isThrownBy(() -> session.enroll(june2));
        assertThatNoException().isThrownBy(() -> session.enroll(june3));
    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '모집중'일 경우에 수강신청이 가능하다(예외를 던지지 않는다.")
    void enroll_SessionStatus_OPENED_NoException() {
        Session session = SessionCreator.create(3L, SessionStatus.OPENED);

        assertThatNoException().isThrownBy(() -> session.enroll(june1));
    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '준비중'일 경우에 수깅신청시 예외를 던진다.")
    void enroll_SessionStatus_READY_ThrowException() {
        Session session = SessionCreator.create(3L, SessionStatus.READY);

        assertThatThrownBy(() -> session.enroll(june1))
                .isInstanceOf(CannotEnrollException.class);

    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '종료'일 경우에 수깅신청시 예외를 던진다.")
    void enroll_SessionStatus_CLOSED_ThrowException() {
        Session session = SessionCreator.create(3L, SessionStatus.CLSOED);

        assertThatThrownBy(() -> session.enroll(june1))
                .isInstanceOf(CannotEnrollException.class);
    }

}