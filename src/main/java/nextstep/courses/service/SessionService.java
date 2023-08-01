package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service("sessionService")
public class SessionService {

    @Resource
    private SessionRepository sessionRepository;

    @Transactional
    public void enrolment(NsUser student, long sessionId) {
        findSession(sessionId)
                .enrolment(student);
    }

    @Transactional
    public void startRecruiting(long sessionId) {
        findSession(sessionId)
                .startRecruiting();
    }

    @Transactional
    public void startSession(long sessionId) {
        findSession(sessionId)
                .startSession();
    }

    @Transactional
    public void endSession(long sessionId) {
        findSession(sessionId)
                .endSession();
    }

    private Session findSession(long courseId) {
        return Optional.ofNullable(sessionRepository.findById(courseId))
                .orElseThrow();
    }
}
