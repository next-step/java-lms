package nextstep.courses.domain;

public enum LectureStatus {
    PREPARING("준비중"),
    ENROLLING("모집중"),
    FINISHED("종료");

    private final String status;

    LectureStatus(String status) {
        this.status = status;
    }

    public String statusValue() {
        return status;
    }

    public boolean isEnrollmentPossible() {
        return this == ENROLLING;
    }
}
