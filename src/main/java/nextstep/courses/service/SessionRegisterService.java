package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;

public class SessionRegisterService {

    private SessionRepository sessionRepository;

    public SessionRegisterService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void registerSession(Long id) {
        Session session = sessionRepository.findById(id);
        session.addStudent(new Student());
    }

}
