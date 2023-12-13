package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageType;
import nextstep.courses.domain.course.session.*;
import nextstep.courses.domain.course.session.image.Images;
import nextstep.courses.service.CourseService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    private Images images;
    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Duration duration;
    private SessionState sessionState;
    private Course course;
    private Sessions sessions;
    private Session session;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        course = new Course(1L, "math", 1, new Sessions(), 1L, localDateTime, null);
        image = new Image(1000, ImageType.GIF, Image.WIDTH_MIN, Image.HEIGHT_MIN, 1L, localDateTime);
        images = new Images(List.of(image));
        payment = new Payment("1", 1L, 3L, 1000L);
        localDate = LocalDate.of(2023, 12, 5);
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.FREE, 0L, Integer.MAX_VALUE);
        session = new Session(1L, images, duration, sessionState, new Applicants(),
                RecruitStatus.NOT_RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);
        sessions = new Sessions();
        sessions.add(session);
    }

    @Test
    @DisplayName("주어진 강의를 과정에 추가하면 과정에 강의가 추가된다.")
    void addSession_success() {
        when(courseRepository.findById(course.getId())).thenReturn(course);
        assertThat(course.sessionSize()).isEqualTo(0);

        courseService.addSession(course.getId(), session);
        assertThat(course.sessionSize()).isEqualTo(1);
    }
}
