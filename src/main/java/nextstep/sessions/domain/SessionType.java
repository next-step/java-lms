package nextstep.sessions.domain;

public enum SessionType {
    FREE, PAID;

    public static SessionType determineSessionType(int price) {
        if (price == 0) {
            return FREE;
        }
        return PAID;
    }
}
