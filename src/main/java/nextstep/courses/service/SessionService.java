package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "coverImageRepository")
    private CoverImageRepository coverImageRepository;

    @Transactional
    public void save(Session session) {
        Long sessionId = sessionRepository.save(session);
        CoverImage coverImage = session.coverImage();
        coverImageRepository.save(coverImage, sessionId);
    }

    public void enroll(Payment payment) throws CannotEnrollException {
        CoverImage coverImage = coverImageRepository.findBySessionId(payment.getSessionId());
        Session session = sessionRepository.findById(payment.getSessionId(), coverImage);
        List<NsUserSession> nsUserSessions = sessionRepository.getNsUserSessions(payment.getSessionId());

        Enrollment enrollment = new Enrollment(session, nsUserSessions);
        NsUserSession nsUserSession = enrollment.enroll(payment);

        sessionRepository.saveNsUserSession(nsUserSession);
    }
}
