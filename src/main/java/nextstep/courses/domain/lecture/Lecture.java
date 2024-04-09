package nextstep.courses.domain.lecture;

public interface Lecture {

    boolean isRegistrationAvailable();

    default boolean isRecruitmentOpen(LectureStatus lectureStatus) {
        return LectureStatus.RECRUITING == lectureStatus;
    }
}
