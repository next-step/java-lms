package nextstep.courses.domain;

public enum LectureStatus {
    PREPARING,
    ONGOING,
    ENDED;

    public boolean isEnded() {
        return this == ENDED;
    }
}
