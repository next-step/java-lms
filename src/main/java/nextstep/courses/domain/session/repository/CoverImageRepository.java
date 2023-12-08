package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.coverimage.CoverImage;

public interface CoverImageRepository {

    CoverImage findById(Long id);
}
