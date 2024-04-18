package nextstep.sessions.domain;

public enum SessionRecruitingStatus {
    NON_RECRUITING, RECRUITING;

    public boolean isNotRecruiting() {
        return this != RECRUITING;
    }
}
