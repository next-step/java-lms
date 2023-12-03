package nextstep.courses.domain.repository;

import nextstep.courses.domain.Image;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);
}
