package nextstep.courses.domain.coverimage;

import nextstep.courses.domain.lectures.LectureEntity;

public interface LectureCoverImageMappingRepository {
  void save(LectureEntity lecture, CoverImages coverImages);
}
