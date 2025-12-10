package nextstep.courses.service;

import java.util.List;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPolicy;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionManageService {

    private final SessionRepository sessionRepository;

    public SessionManageService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createFreeSession(Long courseId, int term, String startDay, String endDay,
                                  List<SessionCoverImage> images) {
        SessionPolicy sessionPolicy = SessionPolicy.free();
        Session session = sessionPolicy.createSession(courseId, term, startDay, endDay, new SessionCoverImages(images));
        sessionRepository.save(session);
    }

    public void createPaidSession(Long courseId, int term, String startDay, String endDay,
                                  int maxCapacity, long tuitionFee,
                                  List<SessionCoverImage> coverImages) {
        SessionPolicy sessionPolicy = SessionPolicy.paid(tuitionFee, maxCapacity);
        Session session = sessionPolicy.createSession(courseId, term, startDay, endDay, new SessionCoverImages(coverImages));
        sessionRepository.save(session);
    }

    public void openSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.start();
        sessionRepository.updateState(sessionId, session.getProgressState());
    }

    public void closeSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.finish();
        sessionRepository.updateState(sessionId, session.getProgressState());
    }

    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }
}