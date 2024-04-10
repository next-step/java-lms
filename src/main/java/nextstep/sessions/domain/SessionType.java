package nextstep.sessions.domain;

public enum SessionType {
    FREE {
        @Override
        public boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents) {
            return true;
        }
    },
    PAID {
        @Override
        public boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents) {
            return currentCountOfStudents < maxOfStudents;
        }
    };

    public abstract boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents);

    public static SessionType determineSessionType(int price) {
        if (price == 0) {
            return FREE;
        }
        return PAID;
    }
}
