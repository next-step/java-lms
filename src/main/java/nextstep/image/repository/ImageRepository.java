package nextstep.image.repository;

import nextstep.image.domain.Image;

public interface ImageRepository {

    void save(Image image);

    Image findById(Long id);
}
