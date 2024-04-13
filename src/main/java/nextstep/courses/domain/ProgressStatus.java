package nextstep.courses.domain;

public enum ProgressStatus {

    PREPARING,
    ONGOING,
    FINISHED;

    public boolean isSame(ProgressStatus progressStatus) {
        return this == progressStatus;
    }
}
