package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
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
        june1 = new Student(0L, 10L);
        june2 = new Student(0L, 10L);
        june3 = new Student(0L, 10L);
        session = SessionCreator.create(2L, SessionStatus.OPENED);
    }

    @Test
    @DisplayName("학생은 수강신청시 강의의 현재 수강인원이 1이 증가한다.")
    void enroll_SessionMaxNumberOfValue_PlusOne() {
        june1.enroll(session);
        assertThat(session.totalStudentNum()).isEqualTo(1);
    }

    @Test
    @DisplayName("학생은 수강신청시 강의의 최대 수강신청 인원을 넘을 경우 예외를 던진다.")
    void enroll_OutOfMaxNumberOfStudent_ThrowException() {
        session.add(june1);
        session.add(june2);

        assertThatThrownBy(() -> session.add(june3))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다.");
    }

}
