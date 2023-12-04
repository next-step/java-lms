package nextstep.courses.repository;

import nextstep.courses.domain.Image.CoverImage;

public interface CoverImageRepository {
    int save(CoverImage image);

    CoverImage findById(Long id);
}
