package nextstep.courses.domain.session;

public enum RecruitStatus {
    NOT_RECRUITING, RECRUITING;

    public boolean isNotRecruiting() {
        return this.equals(NOT_RECRUITING);
    }
}
