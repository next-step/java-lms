package nextstep.courses.domain;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    @DisplayName("과정에 강의를 추가할 수 있다.")
    void 과정에_강의_추가() {
        ImageInfo coverImage = new ImageInfo("JPG", 0.5, 300, 200);
        Course course = new Course(0L, "TDD, 클린코드", 1, new ArrayList<>(), 0L, LocalDateTime.now(), LocalDateTime.now());
        Session session = new Session(0L, null, "수강신청", SessionType.PAID, new Enrollment(null), coverImage, 10000L, SessionTest.recruitingPeriod, 10);
        course.addSessions(List.of(session));

        assertThat(course.equals(new Course(0L, "TDD, 클린코드", 1, List.of(session), 0L, LocalDateTime.now(), LocalDateTime.now())));
        assertThat(session.getCourse().equals(course));
    }
}
