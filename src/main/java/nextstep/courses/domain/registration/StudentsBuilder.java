package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.Set;

public class StudentsBuilder {
    public static final int DEFAULT_MAX_USER_COUNT = 30;
    private Set<Student> students;
    private int maxUserCount;

    private StudentsBuilder() {
        this.maxUserCount = DEFAULT_MAX_USER_COUNT;
        this.students = new HashSet<>();
    }

    public static StudentsBuilder aStudentsBuilder() {
        return new StudentsBuilder();
    }

    public StudentsBuilder withStudent(Student student) {
        this.students.add(student);
        return this;
    }

    public StudentsBuilder withMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public Students build() {
        Students students = new Students(maxUserCount, this.students);
        return students;
    }
}
