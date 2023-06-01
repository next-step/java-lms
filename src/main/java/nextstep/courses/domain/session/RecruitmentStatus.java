package nextstep.courses.domain.session;

public enum RecruitmentStatus {
    OPEN, CLOSE;

    public boolean isOpen() {
        return this == OPEN;
    }
}
