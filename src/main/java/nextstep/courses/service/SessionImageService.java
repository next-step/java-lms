package nextstep.courses.service;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionImageFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

    @Transactional
    public void deleteSessionImage(long sessionImageId) throws IOException {
        SessionImage sessionImage = sessionImageFactory.createSessionImage(sessionImageRepository.findById(sessionImageId));
        sessionImage.delete();
    }
}
