package nextstep.courses.domain.session;

public enum SessionType {
    PAID, FREE;

    public static SessionType of(Amount amount) {
        if (amount.isFree()) {
            return FREE;
        }

        return PAID;
    }

    public boolean isSame(SessionType sessionType) {
        return this.equals(sessionType);
    }

    public boolean isFree() {
        return FREE.equals(this);
    }
}
