package nextstep.courses.domain.cover;

import java.util.List;

public interface CoverImageRepository {

    List<CoverImage> findBySessionId(Long sessionId);

    void save(List<CoverImage> coverImages, Long sessionId);
}