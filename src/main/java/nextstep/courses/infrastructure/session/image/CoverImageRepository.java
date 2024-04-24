package nextstep.courses.infrastructure.session.image;

import java.util.Optional;

public interface CoverImageRepository {

    Long save(final CoverImageEntity coverImageEntity);

    Optional<CoverImageEntity> findById(final Long id);

    Optional<CoverImageEntity> findBySessionId(final Long sessionId);
}
