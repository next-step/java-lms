package nextstep.sessions.domain;

public enum SessionType {
    FREE ((currentCountOfStudents, maxOfStudents) -> false),
    PAID ((currentCountOfStudents, maxOfStudents) -> currentCountOfStudents > maxOfStudents);

    private final CapacityExceededCheck capacityExceededCheck;

    SessionType(CapacityExceededCheck capacityExceededCheck) {
        this.capacityExceededCheck = capacityExceededCheck;
    }

    public boolean isCapacityExceeded(int currentCountOfStudents, int maxOfStudents) {
        return capacityExceededCheck.check(currentCountOfStudents, maxOfStudents);
    }

}
