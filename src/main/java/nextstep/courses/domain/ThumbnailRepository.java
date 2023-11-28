package nextstep.courses.domain;

import java.util.List;

public interface ThumbnailRepository {
    List<Thumbnail> findBySessionId(long sessionId);
}
