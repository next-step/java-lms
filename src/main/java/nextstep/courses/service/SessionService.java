package nextstep.courses.service;

import java.util.Optional;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Sessions;
import nextstep.courses.dto.SessionDTO;
import nextstep.users.domain.NsUser;

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

    public void enroll(NsUser user, SessionDTO sessionDTO){
        enrollmentRepository.save(user.getId(), sessionDTO.getId());
        sessionRepository.save(sessionDTO);
    }
}
