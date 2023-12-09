package nextstep.courses.service;

import javax.annotation.Resource;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionStudentsRepository;
import nextstep.courses.domain.Student;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private final SessionRepository sessionRepository;
    @Resource(name = "sessionStudentsRepository")
    private final SessionStudentsRepository studentsRepository;

    public SessionService(SessionRepository sessionRepository, SessionStudentsRepository studentsRepository) {
        this.sessionRepository = sessionRepository;
        this.studentsRepository = studentsRepository;
    }

    @Transactional
    public void enroll(Long sessionId, NsUser user, Integer sessionFee) {
        Session session = sessionRepository.findById(sessionId);
        SessionStudents students = studentsRepository.findBySessionId(sessionId);

        if (session.isEnrollmentPossible(students, sessionFee)) {
            Student student = session.enroll(students, user);
            studentsRepository.save(student);
        }
    }
}
