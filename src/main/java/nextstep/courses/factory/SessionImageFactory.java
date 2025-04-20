package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SessionImageFactory {

    private final ImageHandler imageHandler;

    private final SessionFactory sessionFactory;

    public SessionImageFactory(ImageHandler urlImageHandler, SessionFactory sessionFactory) {
        this.imageHandler = urlImageHandler;
        this.sessionFactory = sessionFactory;
    }

    public SessionImages create(List<SessionImageEntity> sessionImageEntities, SessionEntity sessionEntity) throws IOException {
        SessionImages sessionImages = new SessionImages();
        Session session = sessionFactory.create(sessionEntity);

        if (sessionEntity.getImageUrl() != null && sessionEntity.getImageType() != null) {
            SessionImage sessionImage = new SessionImage(
                session,
                sessionEntity.getImageUrl(),
                imageHandler,
                SessionImageType.fromString(sessionEntity.getImageType())
            );
            sessionImages.add(sessionImage);
        }

        for (SessionImageEntity sessionImageEntity: sessionImageEntities) {
            SessionImage toAdd = new SessionImage(
                sessionImageEntity.getId(),
                session,
                sessionImageEntity.getImageUrl(),
                imageHandler,
                SessionImageType.fromString(sessionEntity.getImageType())
            );
            sessionImages.add(toAdd);
        }

        return sessionImages;
    }
}
