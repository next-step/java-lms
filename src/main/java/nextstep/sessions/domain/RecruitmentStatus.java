package nextstep.sessions.domain;

public enum RecruitmentStatus {
    NOT_RECRUITING, RECRUITING;

    public boolean isNotRecruiting() {
        return this != RECRUITING;
    }
}
