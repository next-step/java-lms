package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

    private List<Student> students;

    public Students(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public boolean compareMaxStudents(int maxStudents) {
        // 수강생들이 제한인원보다 많은가
        return this.students.size() >= maxStudents;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public boolean isContains(Student student) {
        return this.students.contains(student);
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
