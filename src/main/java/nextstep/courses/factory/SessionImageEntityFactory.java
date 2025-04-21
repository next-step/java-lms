package nextstep.courses.factory;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionImageEntityFactory {

    public SessionImageEntity create(SessionImage sessionImage, Long sessionId) {
        return SessionImageEntity.builder()
            .id(sessionImage.id())
            .createdAt(sessionImage.getCreatedAt())
            .updatedAt(sessionImage.getUpdatedAt())
            .deleted(sessionImage.isDeleted())
            .imageUrl(sessionImage.getUrl())
            .imageType(sessionImage.getType().getType())
            .sessionId(sessionId)
            .build();
    }
}
