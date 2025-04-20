package nextstep.courses.factory;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionImagesFactory {

    private final SessionImageFactory sessionImageFactory;

    public SessionImagesFactory(SessionImageFactory sessionImageFactory) {
        this.sessionImageFactory = sessionImageFactory;
    }

    public SessionImages create(List<SessionImageEntity> sessionImageEntities) throws IOException {
        List<SessionImage> resultList = new ArrayList<>();
        for (SessionImageEntity sessionImageEntity : sessionImageEntities) {
            resultList.add(sessionImageFactory.create(sessionImageEntity));
        }
        return new SessionImages(resultList);
    }
}
