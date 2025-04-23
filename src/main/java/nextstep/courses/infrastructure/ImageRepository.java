package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository {
    SessionThumbnail findThumbnailBySessionId(Long sessionId);
}
