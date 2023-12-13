package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;

public interface CoverImageDAO {
    Long save(CoverImage coverImage);

    CoverImage findBySessionId(Long sessionId);
}
