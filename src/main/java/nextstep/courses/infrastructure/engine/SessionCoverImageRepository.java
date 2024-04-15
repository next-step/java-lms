package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.image.SessionCoverImage;

import java.util.Optional;

public interface SessionCoverImageRepository {

    int save(SessionCoverImage coverImage);

    Optional<SessionCoverImage> findById(Long id);

}
