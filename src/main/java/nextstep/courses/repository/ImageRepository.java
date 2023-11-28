package nextstep.courses.repository;

import nextstep.courses.domain.Image;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);
}

