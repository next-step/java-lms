package nextstep.courses.infrastructure;

import java.util.Optional;
import nextstep.courses.entity.ImageEntity;

public interface ImageRepository {

    int save(ImageEntity image);

    Optional<ImageEntity> findById(Long id);
}
