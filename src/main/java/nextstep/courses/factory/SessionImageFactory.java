package nextstep.courses.factory;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionImageFactory {

    public SessionImage createSessionImage(SessionImageEntity sessionImageEntity) throws IOException {
        return new SessionImage(
            sessionImageEntity.getId(),
            sessionImageEntity.isDeleted(),
            sessionImageEntity.getImageUrl(),
            SessionImageType.fromString(sessionImageEntity.getImageType())
        );
    }

    public SessionImage createSessionImage(String imageUrl, String imageType) throws IOException {
        return new SessionImage(imageUrl, SessionImageType.fromString(imageType));
    }

    public SessionImages createSessionImages(List<SessionImageEntity> sessionImageEntities) throws IOException {
        List<SessionImage> resultList = new ArrayList<>();
        for (SessionImageEntity sessionImageEntity : sessionImageEntities) {
            resultList.add(createSessionImage(sessionImageEntity));
        }
        return new SessionImages(resultList);
    }

    public SessionImageEntity createImageEntity(SessionImage sessionImage, Long sessionId) {
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
