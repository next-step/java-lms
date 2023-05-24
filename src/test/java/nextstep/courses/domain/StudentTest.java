package nextstep.courses.domain;

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
}
