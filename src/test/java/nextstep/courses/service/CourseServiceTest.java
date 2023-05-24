package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.users.domain.User;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CourseService 테스트")
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    private static final Long COURSE_ID = 1L;
    private static final String USER_ID = "dkswnkk";

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    private CourseService courseService;

    private Course course;
    private User user;
    private Session session;
    private List<Session> sessions;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository, sessionRepository, userRepository);
        initializeTestData();
    }

    private void initializeTestData() {
        course = createCourse(COURSE_ID);
        user = createUser(USER_ID);
        session = createSession(COURSE_ID);
        sessions = createSessions();
    }

    @Test
    @DisplayName("강의를 생성할 수 있다.")
    void createCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(COURSE_ID);

        assertThat(courseService.createCourse(course)).isEqualTo(COURSE_ID);
        verify(courseRepository).save(course);
    }

    @Test
    @DisplayName("강의를 조회할 수 있다.")
    void getCourse() {
        when(courseRepository.findById(COURSE_ID)).thenReturn(course);

        assertThat(courseService.getCourse(COURSE_ID)).isEqualTo(course);
        verify(courseRepository).findById(COURSE_ID);
    }

    @Test
    @DisplayName("강의를 수정할 수 있다.")
    void updateCourse() {
        when(courseRepository.update(any(Course.class))).thenReturn(1);

        assertThat(courseService.updateCourse(course)).isEqualTo(1);
        verify(courseRepository).update(course);
    }

    @Test
    @DisplayName("강의를 삭제할 수 있다.")
    void deleteCourse() {
        when(courseRepository.delete(COURSE_ID)).thenReturn(1);

        assertThat(courseService.deleteCourse(COURSE_ID)).isEqualTo(1);
        verify(courseRepository).delete(COURSE_ID);
    }

    @Test
    @DisplayName("강의별 세션을 조회할 수 있다.")
    void getSessionsByCourseId() {
        when(sessionRepository.findSessionsByCourseId(COURSE_ID)).thenReturn(sessions);

        assertThat(courseService.getSessionsByCourseId(COURSE_ID)).isEqualTo(sessions);
        verify(sessionRepository).findSessionsByCourseId(COURSE_ID);
    }

    @Test
    @DisplayName("과정을 통해 세션에 사용자를 등록할 수 있다.")
    void enrollUserToSession() {
        when(sessionRepository.findById(COURSE_ID)).thenReturn(session);
        when(userRepository.findByUserId(USER_ID)).thenReturn(Optional.of(user));

        courseService.enrollUserToSession(COURSE_ID, USER_ID);

        verify(sessionRepository, never()).update(session);
    }

    @Test
    @DisplayName("모든 세션 조회할 수 있다.")
    void getAllSessions() {
        when(sessionRepository.findAll()).thenReturn(sessions);

        assertThat(courseService.getAllSessions()).isEqualTo(sessions);
        verify(sessionRepository).findAll();
    }

    private Course createCourse(Long courseId) {
        return new Course(courseId, "TDD, Clean Code with Java", courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    private List<Session> createSessions() {
        List<Session> sessions = new ArrayList<>();
        sessions.add(createSession(1L));
        sessions.add(createSession(2L));
        return sessions;
    }

    private Session createSession(Long sessionId) {
        return new Session(sessionId, "1기", createImage("session1.jpg"), createSessionTime(), SessionType.FREE.getDescription(), SessionStatus.RECRUITING.getDescription(), new Enrollment(10));
    }

    private Image createImage(String uri) {
        try {
            return Image.create("Image", uri, 1024L, "jpg");
        } catch (Exception e) {
            return null;
        }
    }

    private SessionTime createSessionTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 5, 1, 12, 0);
        return new SessionTime(startDateTime, endDateTime);
    }

    private User createUser(String userId) {
        return new User(2L, userId, "pwd", "안주형", "dkswnkk.dev@gmail.com", LocalDateTime.now(), LocalDateTime.now());
    }
}