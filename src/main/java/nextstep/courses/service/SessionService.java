package nextstep.courses.service;

import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
        if(session.image() != null) {
            imageRepository.save(session.image());
        }
    }

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        return sessionRepository.findById(id);
    }
}
