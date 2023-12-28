package nextstep.courses.service;

import java.util.Optional;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Sessions;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Optional<Sessions> sessions(Long courseId){
        Optional<Sessions> resultSessions = sessionRepository.findByCourseId(courseId);
        if(resultSessions.isPresent()){
            Sessions sessions = resultSessions.get();
            sessions.replaceAllEnrollment(enrollmentRepository::findBySessionId);
        }
        return sessionRepository.findByCourseId(courseId);
    }

    public void enroll(NsUser user, Long sessionId){
        NsUsers nsUsers = enrollmentRepository.findBySessionId(sessionId);
        Session session = sessionRepository.findById(sessionId);
        session.replaceEnrollmentNsUsers(e->nsUsers);
        session.enroll(user);
        enrollmentRepository.save(user.getId(), sessionId);
        sessionRepository.save(session);
    }
}
