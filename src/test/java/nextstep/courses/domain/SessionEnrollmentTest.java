package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionEnrollmentTest {

    Student june1;
    Student june2;
    Student june3;

    @BeforeEach
    void setUp() {
        june1 = new Student(0L, 10L);
        june2 = new Student(0L, 10L);
        june3 = new Student(0L, 10L);
    }

    @Test
    @DisplayName("학생은 수강신청시 강의의 현재 수강인원이 1이 증가한다.")
    void enroll_SessionMaxNumberOfValue_PlusOne() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.OPENED, 3l);

        sessionEnrollment.enroll(june1);

        assertThat(sessionEnrollment.totalStudentNum()).isEqualTo(1l);
    }

    @Test
    @DisplayName("수강신청시 최대 수강신청 인원을 넘을 경우 예외를 던진다.")
    void enroll_OutOfMaxNumberOfStudent_ThrowException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.OPENED, 0l);

        assertThatThrownBy(() -> sessionEnrollment.enroll(june1))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다.");
    }

    @Test
    @DisplayName("수강신청시 최대 수강인원을 넘지 않을 경우 예외를 던지지 않는다.")
    void enroll_LessThanMaxNumberOfStudent_NoException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.OPENED, 3l);

        assertThatNoException().isThrownBy(() -> sessionEnrollment.enroll(june1));
        assertThatNoException().isThrownBy(() -> sessionEnrollment.enroll(june2));
        assertThatNoException().isThrownBy(() -> sessionEnrollment.enroll(june3));
    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '모집중'일 경우에 수강신청이 가능하다(예외를 던지지 않는다).")
    void enroll_SessionStatus_OPENED_NoException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.OPENED, 3l);

        assertThatNoException().isThrownBy(() -> sessionEnrollment.enroll(june1));
    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '준비중'일 경우에 수깅신청시 예외를 던진다.")
    void enroll_SessionStatus_READY_ThrowException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.READY, 3l);

        assertThatThrownBy(() -> sessionEnrollment.enroll(june1))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("현재는 수강신청을 할 수 없는 강의 상태입니다.");
    }

    @Test
    @DisplayName("강의 상태(SessionStatus) '종료'일 경우에 수깅신청시 예외를 던진다.")
    void enroll_SessionStatus_CLOSED_ThrowException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.CLSOED, 3l);

        assertThatThrownBy(() -> sessionEnrollment.enroll(june1))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("현재는 수강신청을 할 수 없는 강의 상태입니다.");
    }

    @Test
    @DisplayName("이미 등록된 학생의 경우 예외를 던진다.")
    void enroll_AlreadyEnrollStudent_Duplicate_ThrowException() {
        SessionEnrollment sessionEnrollment =
                new SessionEnrollment(SessionStatus.OPENED, 3l);

        sessionEnrollment.enroll(june1);

        assertThatThrownBy(() -> sessionEnrollment.enroll(june1))
                .isInstanceOf(AlreadyEnrollmentException.class)
                .hasMessageContaining("학생은 이미 등록한 상태입니다.");
    }
}