package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Students {
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        if (contains(student)) {
            throw new IllegalArgumentException("이미 등록된 학생입니다.");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public boolean contains(Student student) {
        return students.stream()
                .anyMatch(s -> s.equals(student));
    }

    public int size() {
        return students.size();
    }
}
