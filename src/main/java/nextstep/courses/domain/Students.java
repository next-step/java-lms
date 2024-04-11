package nextstep.courses.domain;

import java.util.List;
import java.util.Objects;

public class Students {

    private List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student, int maxStudents) {
        if (this.students.size() >= maxStudents) {
            throw new IllegalArgumentException("수강 인원 초과 과정입니다.");
        }
        this.students.add(student);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Students))
            return false;
        Students students1 = (Students) object;
        return Objects.equals(students, students1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

}
