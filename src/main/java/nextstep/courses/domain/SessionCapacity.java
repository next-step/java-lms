package nextstep.courses.domain;

import nextstep.users.domain.NsStudent;

import java.util.*;

public class SessionCapacity {

    private int numberOfRegisteredStudent;
    private final int maxNumberOfStudents;

    public SessionCapacity() {
        this(0, 30);
    }

    public SessionCapacity(final int maxNumberOfStudents) {
        this(0, maxNumberOfStudents);
    }

    public SessionCapacity(final int numberOfRegisteredStudent, final int maxNumberOfStudents) {
        this.numberOfRegisteredStudent = numberOfRegisteredStudent;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public void register() {
        validateCapacityOfStudent();
        numberOfRegisteredStudent++;
    }

    private void validateCapacityOfStudent() {
        if (numberOfRegisteredStudent + 1 > maxNumberOfStudents) {
            throw new IllegalStateException("현재 최대 수강 인원이 초과 되었습니다.");
        }
    }

    public int getNumberOfRegisteredStudent() {
        return numberOfRegisteredStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCapacity that = (SessionCapacity) o;
        return numberOfRegisteredStudent == that.numberOfRegisteredStudent && maxNumberOfStudents == that.maxNumberOfStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfRegisteredStudent, maxNumberOfStudents);
    }

}
