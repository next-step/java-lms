package nextstep.courses.domain.session;

import java.util.List;

public interface CoverImageRepository {
    int save(CoverImage coverImage, Long sessionId);

    List<CoverImage> findBySessionId(Long id);
}
