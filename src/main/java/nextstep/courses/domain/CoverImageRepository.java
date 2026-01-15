package nextstep.courses.domain;

import nextstep.courses.domain.image.CoverImage;

public interface CoverImageRepository {

    int save(CoverImage coverImage);

    CoverImage findBySessionId(Long sessionId);
}
