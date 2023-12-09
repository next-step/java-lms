package nextstep.courses.domain.image;

import java.util.Optional;

public interface ImageRepository {
    long save(CoverImage coverImage);

    Optional<CoverImage> findById(Long id);
}
