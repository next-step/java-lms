package nextstep.sessions.domain;

public enum SessionType {
    FREE {
        @Override
        public boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents) {
            return false;
        }
    },
    PAID {
        @Override
        public boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents) {
            return currentCountOfStudents > maxOfStudents;
        }
    };

    public abstract boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents);

}
