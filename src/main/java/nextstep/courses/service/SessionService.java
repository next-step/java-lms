package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("sessionService")
@Transactional
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionEnrollRepository")
    private SessionEnrollRepository sessionEnrollRepository;

    @Transactional
    public Long makeSession(Long courseId, SessionVO sessionVO) {
        Session session = new Session(courseId, sessionVO);
        return sessionRepository.save(session);
    }

    @Transactional
    public Long enrollStudent(Session session, Student student, Payment payment) {
        LocalDateTime enrollTime = LocalDateTime.now();
        session.enroll(payment, enrollTime);
        SessionEnroll sessionEnroll = new SessionEnroll(session.getId(), student.getId(), payment.getId(), enrollTime);
        return sessionEnrollRepository.save(sessionEnroll);
    }
}
