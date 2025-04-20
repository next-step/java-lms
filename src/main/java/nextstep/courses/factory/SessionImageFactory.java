package nextstep.courses.factory;

import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionImageFactory {

    private final ImageHandler imageHandler;

    public SessionImageFactory(ImageHandler urlImageHandler) {
        this.imageHandler = urlImageHandler;
    }

    public SessionImage create(SessionImageEntity sessionImageEntity) throws IOException {
        return new SessionImage(
            sessionImageEntity.getId(),
            sessionImageEntity.isDeleted(),
            sessionImageEntity.getImageUrl(),
            imageHandler,
            SessionImageType.fromString(sessionImageEntity.getImageType())
        );
    }
}
