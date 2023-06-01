package nextstep.courses.service;

import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.domain.session.SessionCoverImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionCoverImageService")
public class SessionCoverImageService {

    private final SessionCoverImageRepository sessionCoverImageRepository;

    public SessionCoverImageService(SessionCoverImageRepository sessionCoverImageRepository) {
        this.sessionCoverImageRepository = sessionCoverImageRepository;
    }

    @Transactional
    public int save(SessionCoverImage coverImage, Long sessionId) {
        return sessionCoverImageRepository.save(coverImage, sessionId);
    }

    @Transactional(readOnly = true)
    public SessionCoverImage findById(Long sessionCoverImageId) {
        return sessionCoverImageRepository.findById(sessionCoverImageId);
    }
}
