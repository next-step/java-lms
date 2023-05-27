package nextstep.courses.service;

import nextstep.courses.RegisterCourseException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.ragistration.Registration;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCourseType;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionService sessionService;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;


    public CourseService(CourseRepository courseRepository, SessionService sessionService, RegistrationService registrationService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
        this.registrationService = registrationService;
        this.userRepository = userRepository;
    }

    public void registerSession(long courseId, long sessionId, long userId) {
        final Course course = courseRepository.findById(courseId);
        final Session session = sessionService.findSessionById(sessionId);
        course.receiveSession(List.of(session));

        course.checkIsRegisterSession(sessionId, 1);

        registrationService.save(Registration.receipt(courseId, sessionId, userId));
    }

    /**
     * 파라미터 UserCourseType courseType 는 선발기준이며
     * 이 선발기준이 NsUser 안의 기준과 맞아야 수강신청이 가능하게 만들었습니다.
     */
    @Transactional
    public void approveRegistration(long courseId, long sessionId, String userId, UserCourseType courseType) {
        final NsUser nsUser = userRepository.findByUserId(userId).orElseThrow(() -> new RegisterCourseException("해당하는 수강생을 확인할 수 없습니다."));
        final Session session = sessionService.findSessionById(sessionId);
        final Registration registration = registrationService.findByCourseIdAndSessionIdAndUserId(courseId, sessionId, nsUser.getId());

        registration.approve(nsUser, courseType);
        session.registerSession(registration);

        registrationService.update(registration);
        sessionService.update(session, courseId);
    }
}
