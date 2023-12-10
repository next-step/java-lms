package nextstep.courses.domain.lectures;

public interface LectureRepository {
    int save(Lecture lecture);
    Lecture findLectureById(Long id);
}
