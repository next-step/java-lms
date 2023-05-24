package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudentTest {

    @DisplayName("학생이 한 Session을 수강 신청하면 Session의 수강 신청 인원이 1 증가한다.")
    @Test
    void name() {
        Student student = new Student();
        Session session = new Session();
        session.setStatus(OPENED);
        long maxCount = 3L;
        session.setMaxNumOfStudent(maxCount);
        student.enroll(session);

        assertThat(session.totalStudentNum()).isEqualTo(1);
    }

    @DisplayName("최대 수강 인원과 현재 인원이 동일하다면 예외를 발생한다.")
    @Test
    void name1() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Session session = new Session();
        session.setStatus(OPENED);
        long maxCount = 2L;
        session.setMaxNumOfStudent(maxCount);

        student1.enroll(session);
        student2.enroll(session);
        Assertions.assertThatThrownBy(() -> student3.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("최대 수강 인원보다 현재 인원이 같거나 적다면 예외를 발생하지 않는다.")
    @Test
    void name2() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Session session = new Session();
        session.setStatus(OPENED);
        long maxCount = 3L;
        session.setMaxNumOfStudent(maxCount);

        assertAll(
                () -> assertThatNoException()
                        .isThrownBy(() -> student1.enroll(session)),
                () -> assertThatNoException()
                        .isThrownBy(() -> student2.enroll(session)),
                () -> assertThatNoException()
                        .isThrownBy(() -> student3.enroll(session))
        );
    }

    @DisplayName("강의의 상태가 `준비중`일 경우 수강 신청 시 예외가 발생한다.")
    @Test
    void name3() {
        Student student = new Student();
        Session session = new Session();
        session.setStatus(READY);
        long maxCount = 3L;
        session.setMaxNumOfStudent(maxCount);

        assertThatThrownBy(() -> student.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("강의의 상태가 `종료`일 경우 수강 신청 시 예외가 발생한다.")
    @Test
    void name4() {
        Student student = new Student();
        Session session = new Session();
        session.setStatus(CLOSED);
        long maxCount = 3L;
        session.setMaxNumOfStudent(maxCount);

        assertThatThrownBy(() -> student.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("강의의 상태가 `모집중`일 경우 수강 신청 시 예외가 발생하지 않는다.")
    @Test
    void name5() {
        Student student = new Student();
        Session session = new Session();
        session.setStatus(OPENED);
        long maxCount = 3L;
        session.setMaxNumOfStudent(maxCount);

        assertThatNoException()
                .isThrownBy(() -> student.enroll(session));
    }
}
