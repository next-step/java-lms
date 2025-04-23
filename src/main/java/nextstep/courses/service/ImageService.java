package nextstep.courses.service;

import lombok.RequiredArgsConstructor;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.infrastructure.ImageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public SessionThumbnail findThumbnailBySessionId(Long sessionId) {
        return imageRepository.findThumbnailBySessionId(sessionId);
    }

}
