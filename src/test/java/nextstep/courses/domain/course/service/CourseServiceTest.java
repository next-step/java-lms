package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.fixture.CourseFixtures;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.courses.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    @DisplayName("주어진 강의를 과정에 추가하면 과정에 강의가 추가된다.")
    void addSession_success() {
        Course course = CourseFixtures.course();

        when(courseRepository.findById(course.id())).thenReturn(course);

        Session session = SessionFixtures.createdFreeSession();
        courseService.addSession(course.id(), session);

        verify(sessionRepository).updateCourseId(course.id(), session);
    }
}
