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

    public void enroll(Session session, NsUser nsUser, Payment payment) {
        Student student = Student.ofWait(nsUser);
        session.enroll(nsUser, payment);
        studentsRepository.save(session.id(), student);
    }

    public long save(Session session) {
        long sessionId = sessionRepository.save(session);
        imageRepository.save(session.images(), sessionId);
        return sessionId;
    }

    public void approval(long sessionId, NsUser nsUser) {
        Session session = findById(sessionId);
        Student student = session.approvalSession(nsUser);
        studentsRepository.updateState(session.id(), student);
    }

    public void cancel(long sessionId, NsUser nsUser) {
        Session session = findById(sessionId);
        Student student = session.approvalCancel(nsUser);
        studentsRepository.updateState(session.id(), student);
    }

    public Session findById(long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Images images = imageRepository.findImagesBySessionId(session.id());
        Students students = studentsRepository.findBySessionId(session.id());

        session.changeImages(images);
        session.changeStudents(students);

        return session;
    }
}
