package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.session.*;
import nextstep.courses.service.CourseService;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Duration duration;
    private SessionState sessionState;
    private Course course;
    private Session session;

    @BeforeEach
    void setUp() {
        course = new Course("math", 1, 1L);
        image = new Image(1000, "jpeg", Image.WIDTH_MIN, Image.HEIGHT_MIN);
        payment = new Payment("1", 1L, 3L, 1000L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.FREE, 1000L, 10);
        session = new Session(1L, image, duration, sessionState, new Applicants(),
                Session.Status.RECRUIT, 1L, localDateTime, localDateTime);
    }

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    @DisplayName("주어진 강의를 과정에 추가하면 과정에 강의가 추가된다.")
    void addSession_success() {
        when(courseRepository.findById(course.getId())).thenReturn(course);
        assertThat(course.sessionSize()).isEqualTo(0);

        courseService.addSession(course.getId(), session);
        assertThat(course.sessionSize()).isEqualTo(1);
    }
}
