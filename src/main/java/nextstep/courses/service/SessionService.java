package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionStudentsRepository;
import nextstep.courses.domain.Student;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionStudentsRepository studentsRepository;

    public SessionService(SessionRepository sessionRepository, SessionStudentsRepository studentsRepository) {
        this.sessionRepository = sessionRepository;
        this.studentsRepository = studentsRepository;
    }

    public void enroll(Long sessionId, NsUser user, Integer sessionFee) {
        Session session = sessionRepository.findById(sessionId);
        SessionStudents students = studentsRepository.findBySessionId(sessionId);

        if (session.isEnrollmentPossible(students, sessionFee)) {
            Student student = session.enroll(students, user);
            studentsRepository.save(student);
        }

    }
}
