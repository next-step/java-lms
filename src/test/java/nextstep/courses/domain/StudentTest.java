package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudentTest {


    private Student firstStudent;
    private Student secondStudent;
    private Student thridStudent;

    private Session session;

    @BeforeEach
    void setUp() {
        this.firstStudent = new Student(0L, 1L);
        this.secondStudent = new Student(1L, 1L);
        this.thridStudent = new Student(2L, 1L);
        long maxNumOfStudent = 2L;
        this.session = SessionCreator.create(maxNumOfStudent, OPENED);
    }

    @DisplayName("학생이 한 Session을 수강 신청하면 Session의 수강 신청 인원이 1 증가한다.")
    @Test
    void name() {
        firstStudent.enroll(session);
        assertThat(session.totalStudentNum()).isEqualTo(1);
    }

    @DisplayName("최대 수강 인원과 현재 인원이 동일하다면 예외를 발생한다.")
    @Test
    void name1() {
        firstStudent.enroll(session);
        secondStudent.enroll(session);
        Assertions.assertThatThrownBy(() -> thridStudent.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("최대 수강 인원보다 현재 인원이 같거나 적다면 예외를 발생하지 않는다.")
    @Test
    void name2() {
        Session session = SessionCreator.create(3L, OPENED);
        assertAll(
                () -> assertThatNoException()
                        .isThrownBy(() -> firstStudent.enroll(session)),
                () -> assertThatNoException()
                        .isThrownBy(() -> secondStudent.enroll(session)),
                () -> assertThatNoException()
                        .isThrownBy(() -> thridStudent.enroll(session))
        );
    }

    @DisplayName("강의의 상태가 `준비중`일 경우 수강 신청 시 예외가 발생한다.")
    @Test
    void name3() {
        Session session = SessionCreator.create(3L, READY);

        assertThatThrownBy(() -> firstStudent.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("강의의 상태가 `종료`일 경우 수강 신청 시 예외가 발생한다.")
    @Test
    void name4() {
        Session session = SessionCreator.create(3L, CLOSED);

        assertThatThrownBy(() -> firstStudent.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }

    @DisplayName("강의의 상태가 `모집중`일 경우 수강 신청 시 예외가 발생하지 않는다.")
    @Test
    void name5() {
        assertThatNoException()
                .isThrownBy(() -> firstStudent.enroll(session));
    }
}
