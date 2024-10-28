package nextstep.session.domain.image;

import java.util.List;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);

    List<Image> findBySessionId(Long sessionId);
}
