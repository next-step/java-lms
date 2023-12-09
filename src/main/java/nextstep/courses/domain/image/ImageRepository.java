package nextstep.courses.domain.image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    int save(Long sessionId, CoverImage coverImage);

    Optional<CoverImage> findById(Long id);

    List<CoverImage> findAllBySessionId(Long sessionId);
}
