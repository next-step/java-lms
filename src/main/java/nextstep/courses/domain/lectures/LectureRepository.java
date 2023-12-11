package nextstep.courses.domain.lectures;

public interface LectureRepository {
    int save(LectureEntity entity);
    LectureEntity findById(Long id);
}
