package nextstep.courses.repository;

import nextstep.courses.domain.CoverImage;

public interface CoverImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);
}
