package nextstep.courses.domain.lectures;

public interface LectureRepository {
    int save(Lecture lecture);

    Lecture findFreeLectureById(Long id);
    Lecture findPaidLectureById(Long id);
}
