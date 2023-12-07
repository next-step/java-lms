package nextstep.courses.service;

import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ImageRepository imageRepository;
    private final StudentsRepository studentsRepository;

    public SessionService(SessionRepository sessionRepository, ImageRepository imageRepository, StudentsRepository studentsRepository) {
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
        this.studentsRepository = studentsRepository;
    }

    public void enroll(NsUser nsUser, Long sessionId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(nsUser, payment);
    }

    public long save(Session session) {
        return sessionRepository.save(session);
    }

    public Session findById(long id) {
        Session session = sessionRepository.findById(id);
        Image image = imageRepository.findBySessionId(session.id());
        Students students = studentsRepository.findBySessionId(session.id());

        session.changeImage(image);
        session.changeStudents(students);

        return session;
    }
}
