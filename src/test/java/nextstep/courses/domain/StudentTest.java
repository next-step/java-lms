package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @DisplayName("학생이 한 Session을 수강 신청하면 Session의 수강 신청 인원이 1 증가한다.")
    @Test
    void name() {
        Student student = new Student();
        Session session = new Session();
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
        long maxCount = 2L;
        session.setMaxNumOfStudent(maxCount);

        student1.enroll(session);
        student2.enroll(session);
        Assertions.assertThatThrownBy(() -> student3.enroll(session))
                .isInstanceOf(CannotEnrollException.class);
    }
}
