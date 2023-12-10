package nextstep.courses.domain.session;

public enum SessionType {

    FREE,
    PAID,
    ;

    public boolean isFree() {
        return this.equals(FREE);
    }
}
