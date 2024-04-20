package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.image.SessionCoverImage;

import java.util.List;

public interface SessionCoverImageRepository {

    int[] saveAll(List<SessionCoverImage> coverImage);

    List<SessionCoverImage> findAllBySessionId(Long sessionId);

}
