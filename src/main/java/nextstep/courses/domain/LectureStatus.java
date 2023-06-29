package nextstep.courses.domain;

public enum LectureStatus {
    PREPARING, RECRUITING, COMPLETED;
    public boolean isRecruiting() {
        return this.equals(RECRUITING);
    }
}
