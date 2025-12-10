package nextstep.courses.service;

import nextstep.courses.domain.image.SessionCoverImage;
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
                                  int imageWidth, int imageHeight, String imageExtension, long imageBytes) {
        SessionCoverImage coverImage = new SessionCoverImage(null, imageWidth, imageHeight, imageExtension, imageBytes);
        SessionPolicy sessionPolicy = SessionPolicy.free();
        Session session = sessionPolicy.createSession(courseId, term, startDay, endDay, coverImage);
        sessionRepository.save(session);
    }

    public void createPaidSession(Long courseId, int term, String startDay, String endDay,
                                  int maxCapacity, long tuitionFee,
                                  int imageWidth, int imageHeight, String imageExtension, long imageBytes) {
        SessionCoverImage coverImage = new SessionCoverImage(null, imageWidth, imageHeight, imageExtension, imageBytes);
        SessionPolicy sessionPolicy = SessionPolicy.paid(tuitionFee, maxCapacity);
        Session session = sessionPolicy.createSession(courseId, term, startDay, endDay, coverImage);
        sessionRepository.save(session);
    }

    public void openSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.open();
        sessionRepository.updateState(sessionId, session.getState());
    }

    public void closeSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.close();
        sessionRepository.updateState(sessionId, session.getState());
    }

    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }
}