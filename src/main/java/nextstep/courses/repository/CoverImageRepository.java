package nextstep.courses.repository;

import nextstep.courses.domain.Image.CoverImage;

import java.util.List;

public interface CoverImageRepository {
    int save(CoverImage image, Long sessionId);

    CoverImage findById(Long id);

    List<CoverImage> findBySessionId(Long sessionId);
}
