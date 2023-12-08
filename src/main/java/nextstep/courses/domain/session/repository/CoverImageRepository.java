package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.coverimage.CoverImage;

import java.util.Optional;

public interface CoverImageRepository {

    Optional<CoverImage> findById(Long id);
}
