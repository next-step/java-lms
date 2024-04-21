package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.CoverImages;

import java.util.List;

public interface CoverImageRepository {
    int save(Long sessionId, CoverImages coverImages);
    List<CoverImage> findBySessionId(Long sessionId);
}
