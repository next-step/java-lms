package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;

import java.util.List;

public interface CoverImageDAO {
    void saveAll(List<CoverImage> coverImages);

    List<CoverImage> findBySessionId(Long sessionId);
}
