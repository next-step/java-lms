package nextstep.courses.domain.course.image;

import java.util.Optional;

public interface ImageRepository {
    Optional<Image> findById(Long id);

    int save(Image image);
}
