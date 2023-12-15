package nextstep.image.domain;

import java.util.Optional;

public interface ImageRepository {

    Optional<Image> findById(Long imageId);
}
