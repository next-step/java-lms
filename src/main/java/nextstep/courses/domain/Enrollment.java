package nextstep.courses.domain;

import java.util.Objects;

public class Enrollment {
    private final int capacity;
    private final int sizeOfStudents;
    private final SessionStatus sessionStatus;

    public Enrollment(int capacity, int sizeOfStudents, SessionStatus sessionStatus) {
        if (sizeOfStudents > capacity) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
        this.capacity = capacity;
        this.sizeOfStudents = sizeOfStudents;
        this.sessionStatus = sessionStatus;
    }

    public Enrollment enroll() {
        if (sessionStatus != SessionStatus.ENROLLING) {
            throw new IllegalArgumentException("수강신청 상태가 아니라 수강신청할 수 없습니다.");
        }
        return new Enrollment(capacity, sizeOfStudents + 1, sessionStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return capacity == that.capacity && sizeOfStudents == that.sizeOfStudents && sessionStatus == that.sessionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, sizeOfStudents, sessionStatus);
    }
}
