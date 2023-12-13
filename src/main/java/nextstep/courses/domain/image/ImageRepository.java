package nextstep.courses.domain.image;

import java.util.List;

public interface ImageRepository {

    List<Image> findAllBySessionId(Long sessionId);
}
