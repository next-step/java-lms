package nextstep.courses.repository;

import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.courses.domain.session.cover.CoverImages;

public interface CoverImageRepository {
    int save(CoverImage coverImage, Long sessionId);
    CoverImages findBySessionId(Long sessionId);
}
