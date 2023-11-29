package nextstep.courses.repository;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Images;

public interface ImageRepository {
    int save(Image image, Long sessionId);

    Image findById(Long id);

    Images findAllBySessionId(Long id);
}

