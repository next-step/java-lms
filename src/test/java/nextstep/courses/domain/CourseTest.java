package nextstep.courses.domain;

import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    public static Course course = new Course(1L, "TDD with java", 1L);

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("과정은 강의가 추가될 수 있다.")
    void course_AddSession_Test() {
        assertThat(course.getSessions().size()).isEqualTo(0);
        Session session1 = Session.valueOf(1L, "과제3 - 사다리게임", course.getId()
                , EnrollmentStatus.PREPARING, LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Session session2 = Session.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId()
                , EnrollmentStatus.RECRUITING, LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session1);
        course.addSession(session2);
        assertThat(course.getSessions().size()).isEqualTo(2);
    }
}
