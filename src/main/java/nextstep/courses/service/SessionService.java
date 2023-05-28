package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.exception.NoSuchSessionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public void enroll(Long id) {
        Session session = sessionRepository.findById(id);
        if (Objects.isNull(session)) {
            throw new NoSuchSessionException();
        }
        session.enroll();
        sessionRepository.update(session);
    }
}
