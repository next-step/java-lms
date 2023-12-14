package nextstep.courses.domain.course.session.image;

import java.util.Optional;

public interface ImageRepository {
    Optional<Image> findById(Long id);

    Image save(Long sessionId, Image image);

    Images findAllBySessionId(Long sessionId);
}
