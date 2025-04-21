package nextstep.courses.service;

import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.factory.SessionImageFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class SessionImageService {

    private final SessionImageRepository sessionImageRepository;
    private final SessionImageFactory sessionImageFactory;
    private final ImageHandler imageHandler;

    public SessionImageService(SessionImageRepository sessionImageRepository, SessionImageFactory sessionImageFactory, ImageHandler imageHandler) {
        this.sessionImageRepository = sessionImageRepository;
        this.sessionImageFactory = sessionImageFactory;
        this.imageHandler = imageHandler;
    }

    public void createSessionImage(long sessionId, String imageUrl, String imageType) throws IOException {
        SessionImage sessionImage = new SessionImage(imageUrl, imageHandler, SessionImageType.fromString(imageType));
        sessionImageRepository.save(sessionImage.toSessionImageEntity(sessionId));
    }

    @Transactional
    public void deleteSessionImage(long sessionImageId) throws IOException {
        SessionImage sessionImage = sessionImageFactory.createSessionImage(sessionImageRepository.findById(sessionImageId));
        sessionImage.delete();
    }
}
