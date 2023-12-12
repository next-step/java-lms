package nextstep.courses.type;

public enum RecruitmentStatus {
    RECRUITING, NOT_RECRUITING;

    public boolean isNotRecruiting() {
        return this.equals(NOT_RECRUITING);
    }
}
