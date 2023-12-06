package nextstep.courses.domain.image;

import nextstep.courses.domain.course.Course;

public interface ImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);
}
