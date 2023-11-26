package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;

import java.util.Objects;

public class FreeEnrollmentStrategy implements EnrollmentStrategy {

    private final Students students;

    public FreeEnrollmentStrategy() {
        this(new Students());
    }

    public FreeEnrollmentStrategy(Students students) {
        this.students = students;
    }

    @Override
    public void enroll(long amount,
                       Student student,
                       Students students) {
        students.enrol(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeEnrollmentStrategy that = (FreeEnrollmentStrategy) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
