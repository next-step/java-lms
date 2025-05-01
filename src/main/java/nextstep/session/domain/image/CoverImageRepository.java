package nextstep.session.domain.image;

import java.util.List;

public interface CoverImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);
    List<CoverImage> findBySessionId(Long sessionId);
}
