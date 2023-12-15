package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCover;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.RegistrationRepository;
import nextstep.courses.domain.repository.SessionCoverRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionCoverRepository sessionCoverRepository;

    public SessionService(CourseRepository courseRepository, RegistrationRepository registrationRepository, SessionRepository sessionRepository, UserRepository userRepository, SessionCoverRepository sessionCoverRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.sessionCoverRepository = sessionCoverRepository;
    }

    @Transactional
    public void registerSession(String userId, Long sessionId, Long amount) {
        NsUser user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유효한 userId가 아닙니다."));
        Session session = retrieveSession(sessionId);

        registrationRepository.save(Registration.register(user, session, amount));
    }

    private Session retrieveSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("유효한 sessionId가 아닙니다."));

        Course course = courseRepository.findById(session.course().id());
        SessionCover sessionCover = sessionCoverRepository.findById(session.sessionCover().id());
        List<Registration> registrations = registrationRepository.findAllBySessionId(sessionId);
        List<NsUser> users = userRepository.findAllByIds(registrations.stream().map(r -> r.nsUser().getId()).collect(Collectors.toList()));

        return Session.fromSession(session, sessionCover, course);
    }


}
