package nextstep.courses.domain.session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EnrolledStudents {

    private final Set<Student> enrolledStudents;

    private EnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = new HashSet<>(enrolledStudents);
    }

    public static EnrolledStudents of(Set<Student> enrolledStudents) {
        return new EnrolledStudents(enrolledStudents);
    }

    public Set<Student> getEnrolledStudents() {
        return Collections.unmodifiableSet(enrolledStudents);
    }

    public void add(Student student) {
        this.enrolledStudents.add(student);
    }

    public boolean contains(Student student) {
        return enrolledStudents.contains(student);
    }

    public int size() {
        return enrolledStudents.size();
    }
}
