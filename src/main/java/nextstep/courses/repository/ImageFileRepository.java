package nextstep.courses.repository;

import nextstep.courses.domain.ImageFile;

import java.util.List;

public interface ImageFileRepository {
    ImageFile save(ImageFile imageFile);

    ImageFile findById(long id);

    List<ImageFile> findBySessionId(long sessionId);
}
