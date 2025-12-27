package nextstep.courses.repository;

import nextstep.courses.domain.ImageFile;

public interface ImageFileRepository {
    Long save(ImageFile imageFile);

    ImageFile findById(long id);
}
