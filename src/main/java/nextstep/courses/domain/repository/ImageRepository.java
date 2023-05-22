package nextstep.courses.domain.repository;

import nextstep.courses.domain.Image;

public interface ImageRepository {
    long save(Image session);
    Image findById(Long id);
    int update(Image session);
    int delete(Long id);
}
