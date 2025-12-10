package nextstep.courses.infrastructure.mapper;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.image.SessionImageCapacity;
import nextstep.courses.domain.image.SessionImageDimension;
import nextstep.courses.domain.image.SessionImageExtension;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;

public class SessionCoverImageMapper {

    private SessionCoverImageMapper() {
    }

    public static SessionCoverImageEntity toEntity(SessionCoverImage image) {
        return new SessionCoverImageEntity(
            image.getId(),
            image.getSessionId(),
            image.getDimension().width(),
            image.getDimension().height(),
            image.getExtension().name(),
            image.getCapacity().bytes()
        );
    }

    public static SessionCoverImage toDomain(SessionCoverImageEntity entity) {
        return new SessionCoverImage(
            entity.getId(),
            entity.getSessionId(),
            new SessionImageDimension(entity.getWidth(), entity.getHeight()),
            SessionImageExtension.valueOf(entity.getExtension()),
            new SessionImageCapacity(entity.getCapacity())
        );
    }
}
