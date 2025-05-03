package nextstep.courses.service;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionImageService {

    private final SessionImageRepository sessionImageRepository;
    private final SessionImageFactory sessionImageFactory;

    public SessionImageService(SessionImageRepository sessionImageRepository, SessionImageFactory sessionImageFactory) {
        this.sessionImageRepository = sessionImageRepository;
        this.sessionImageFactory = sessionImageFactory;
    }

    public SessionImages getSessionImages(long sessionId) throws IOException {
        List<SessionImage> sessionImages = new ArrayList<>();
        List<SessionImageEntity> sessionImageEntities = getSessionImageEntities(sessionId);
        for (SessionImageEntity sessionImageEntity : sessionImageEntities) {
            sessionImages.add(sessionImageFactory.createSessionImage(sessionImageEntity));
        }
        return new SessionImages(sessionImages);
    }

    public Long createSessionImage(long sessionId, String imageUrl, String imageType) throws IOException {
        SessionImage sessionImage = sessionImageFactory.createSessionImage(imageUrl, imageType);
        return sessionImageRepository.save(sessionImageFactory.createImageEntity(sessionImage, sessionId));
    }

    public void deleteSessionImage(long sessionImageId) {
        sessionImageRepository.delete(sessionImageId);
    }

    @Transactional
    public void deleteSessionImages(long sessionId) {
        List<SessionImageEntity> sessionImageEntities = getSessionImageEntities(sessionId);

        sessionImageEntities.stream()
            .map(SessionImageEntity::getId)
            .map(Long::parseLong)
            .forEach(this::deleteSessionImage);
    }

    private List<SessionImageEntity> getSessionImageEntities(long sessionId) {
        return sessionImageRepository.findAllBySessionId(sessionId);
    }
}
