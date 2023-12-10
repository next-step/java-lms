package nextstep.lms.repository;

import nextstep.lms.domain.CoverImage;

import java.util.List;

public interface CoverImageRepository {
    int save(CoverImage coverImage);

    List<CoverImage> findBySessionId(Long sessionId);
}
