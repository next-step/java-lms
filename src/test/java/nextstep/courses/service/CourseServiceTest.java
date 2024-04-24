package nextstep.courses.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionTest;

@Transactional
@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("새로운 과정을 생성한다.")
    void Save_NewCourse() {
        final Course course = CourseTest.course();
        final long savedCourseId = courseService.save(course);

        final Course savedCourse = courseService.findById(savedCourseId);

        assertThat(savedCourse.id())
                .isEqualTo(savedCourseId);
        assertThat(savedCourse.title())
                .isEqualTo(course.title());
    }

    @Test
    @DisplayName("과정에 강의를 하나 추가한다.")
    void Add_Session() {
        final Course course = CourseTest.course();
        final long savedCourseId = courseService.save(course);

        final Session session = SessionTest.session();
        final long savedSessionId = sessionService.save(session);

        courseService.addNewSession(savedCourseId, savedSessionId);

        final Course savedCourse = courseService.findById(savedCourseId);
        final Session savedSession = sessionService.findById(savedSessionId);

        assertThat(savedCourse.containsSession(savedSession))
                .isTrue();
    }
}
