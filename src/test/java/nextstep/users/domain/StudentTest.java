package nextstep.users.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.Sessions;
import nextstep.courses.domain.SessionsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudentTest {
    @Test
    @DisplayName("수강 신청")
    void addSession() {
        Student student = new Student(NsUserTest.SANJIGI, new Sessions());
        student.addSession(SessionTest.s1);

        assertThat(student.getSessions()).isEqualTo(SessionsTest.ss1);

    }
}
