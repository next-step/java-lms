package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(session.totalStudentNum()).isEqualTo(1);
    }

}
