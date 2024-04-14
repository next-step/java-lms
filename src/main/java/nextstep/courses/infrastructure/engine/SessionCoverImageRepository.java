package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.image.SessionCoverImage;

public interface SessionCoverImageRepository {

    int save(SessionCoverImage coverImage);

    SessionCoverImage findById(Long id);

}
