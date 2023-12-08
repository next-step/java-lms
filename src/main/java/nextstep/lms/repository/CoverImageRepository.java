package nextstep.lms.repository;

import nextstep.lms.domain.CoverImage;

public interface CoverImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);
}
