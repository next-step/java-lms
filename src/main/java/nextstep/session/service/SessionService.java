package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.image.ImageRepository;
import nextstep.session.service.request.SessionFindRequest;
import nextstep.session.service.request.SessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, ImageRepository imageRepository) {
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
    }

    public void save(SessionRequest sessionRequest) {
        Session session = sessionRequest.toDomain();
        imageRepository.save(session.getImage());
        sessionRepository.save(session);
    }

    public void findById(SessionFindRequest sessionFindRequest) {
        Session session = sessionRepository.findById(sessionFindRequest.getSessionId());
    }

}
