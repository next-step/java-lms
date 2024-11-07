package nextstep.courses.domain.cover;

import java.util.Optional;

public interface CoverImageRepository {

    Optional<CoverImage> findBySessionId(Long sessionId);

    void save(CoverImage coverImage, Long sessionId);
}