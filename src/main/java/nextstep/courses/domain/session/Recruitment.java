package nextstep.courses.domain.session;

public enum Recruitment {

    RECRUITING,
    NOT_RECRUITING,
    ;

    public boolean isNotRecruiting() {
        return !this.equals(RECRUITING);
    }
}
