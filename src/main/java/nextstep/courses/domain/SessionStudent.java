package nextstep.courses.domain;

import java.util.Objects;

public class SessionStudent {

    private int numberOfRegisteredStudent;
    private final int maxNumberOfStudents;

    public SessionStudent() {
        this(0, 30);
    }

    public SessionStudent(final int maxNumberOfStudents) {
        this(0, maxNumberOfStudents);
    }

    public SessionStudent(final int numberOfRegisteredStudent, final int maxNumberOfStudents) {
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

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public int getNumberOfRegisteredStudent() {
        return numberOfRegisteredStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent that = (SessionStudent) o;
        return numberOfRegisteredStudent == that.numberOfRegisteredStudent && maxNumberOfStudents == that.maxNumberOfStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfRegisteredStudent, maxNumberOfStudents);
    }

    @Override
    public String toString() {
        return "SessionStudent{" +
                "numberOfRegisteredStudent=" + numberOfRegisteredStudent +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                '}';
    }
}
