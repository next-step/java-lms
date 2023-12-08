package nextstep.session.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.infrastructure.MockCourseRepository;
import nextstep.courses.sevice.CourseService;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageCapacity;
import nextstep.image.domain.ImageCapacityType;
import nextstep.image.domain.ImageSize;
import nextstep.image.domain.ImageType;
import nextstep.session.domain.PaymentType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionPayment;
import nextstep.session.domain.SessionPeriod;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.dto.CreateSessionRequest;
import nextstep.session.dto.EnrollSessionRequest;
import nextstep.session.repository.MockSessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.MockUserRepository;
import nextstep.users.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionServiceTest {

    @Test
    @DisplayName("강의를 수강신청한다.")
    void enroll_session() {
        // given
        EnrollSessionRequest request = new EnrollSessionRequest(1L, 10000, "wlscww");

        // when
        MockSessionRepository sessionRepository = new MockSessionRepository();
        SessionService sessionService = new SessionService(
                sessionRepository,
                new UserService(new MockUserRepository()),
                new CourseService(new MockCourseRepository()));
        sessionService.enrollSession(request);

        // then
        Session findSession = sessionRepository.findById(request.getSessionId());

        assertThat(findSession.getEnrollment().getPayments()).contains(
                new SessionPayment(new NsUser(0L, "tempId", "5678", "이수찬", "email"), PaymentType.COMPLETED),
                new SessionPayment(new NsUser(1L, "wlscww", "1234", "이수찬", "email"), PaymentType.COMPLETED));
        assertThat(findSession.getEnrollment().getParticipants().getParticipants()).contains(
                new NsUser(0L, "tempId", "5678", "이수찬", "email"),
                new NsUser(1L, "wlscww", "1234", "이수찬", "email"));
    }

    @Test
    @DisplayName("강의를 저장한다.")
    void save_session() {
        // given
        CreateSessionRequest request = createRequest();

        // when
        MockSessionRepository sessionRepository = new MockSessionRepository();
        CourseService courseService = new CourseService(new MockCourseRepository());
        SessionService sessionService = new SessionService(
                sessionRepository,
                new UserService(new MockUserRepository()),
                courseService);
        Long sessionId = sessionService.save(request);

        // then
        Session findSession = sessionRepository.findById(sessionId);
        assertThat(findSession).isEqualTo(createExpectedSession());

        Course findCourse = courseService.findCourse(request.getCourseId());
        assertThat(findCourse.getSessions()).isEqualTo(List.of(findSession));
    }

    private CreateSessionRequest createRequest() {
        return new CreateSessionRequest(
                1L,
                LocalDate.of(2023, 12, 20),
                LocalDate.of(2023, 12, 30),
                10,
                ImageCapacityType.KB,
                ImageType.JPEG,
                600,
                400,
                SessionType.PAID,
                SessionStatus.RECRUITING,
                10000
        );
    }

    private Session createExpectedSession() {
        return new Session(
                new SessionPeriod(LocalDate.of(2023, 12, 20), LocalDate.of(2023, 12, 30)),
                new Image(new ImageCapacity(10, "kb"), ImageType.JPEG, new ImageSize(600, 400)),
                SessionType.PAID,
                SessionStatus.RECRUITING,
                10000
        );
    }
}
