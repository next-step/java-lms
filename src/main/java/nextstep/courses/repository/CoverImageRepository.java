package nextstep.courses.repository;

import nextstep.courses.domain.CoverImage;

import java.util.List;

public interface CoverImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);

    List<CoverImage> findAllBySessionId(Long id);
}
