package nextstep.courses.service;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SessionImageService {

    private final SessionImageRepository sessionImageRepository;
    private final SessionImageFactory sessionImageFactory;

    public SessionImageService(SessionImageRepository sessionImageRepository, SessionImageFactory sessionImageFactory) {
        this.sessionImageRepository = sessionImageRepository;
        this.sessionImageFactory = sessionImageFactory;
    }

    public void createSessionImage(long sessionId, String imageUrl, String imageType) throws IOException {
        SessionImage sessionImage = sessionImageFactory.createSessionImage(imageUrl, imageType);
        sessionImageRepository.save(sessionImageFactory.createImageEntity(sessionImage, sessionId));
    }

    public List<SessionImageEntity> findAllBySessionId(long sessionId) {
        return sessionImageRepository.findAllBySessionId(sessionId);
    }

    @Transactional
    public void deleteSessionImage(long sessionImageId) {
        sessionImageRepository.delete(sessionImageId);
    }

    @Transactional
    public void deleteSessionImages(long sessionId) {
        List<SessionImageEntity> sessionImageEntities = findAllBySessionId(sessionId);

        sessionImageEntities.stream()
            .map(SessionImageEntity::getId)
            .map(Long::parseLong)
            .forEach(this::deleteSessionImage);
    }
}
