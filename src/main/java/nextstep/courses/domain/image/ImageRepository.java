package nextstep.courses.domain.image;

import nextstep.courses.domain.session.Images;

public interface ImageRepository {

    int save(Image image);

    Image findById(Long id);

    Images findBySessionId(Long findSessionId);

    void saveAll(Images images);
}
