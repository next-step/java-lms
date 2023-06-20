package nextstep.courses.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Students {

    private final Set<Student> students;

    public Students() {
        this(new HashSet<>());
    }

    public Students(Set<Student> students) {
        this.students = students;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void add(Student student) {
        this.students.add(student);
    }

    public int size(){
        return students.size();
    }
}
