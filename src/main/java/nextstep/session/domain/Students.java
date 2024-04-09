package nextstep.session.domain;

import nextstep.exception.StudentsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Students {

    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }

    public Students() {
        students = new ArrayList<>();
    }


    public void add(Student student) {
        validateDuplicate(student);
        students.add(student);
    }

    private void validateDuplicate(Student student) {
        boolean hasStudent = hasStudent(student);
        if (hasStudent) {
            throw new StudentsException("이미 등록된 회원입니다.");
        }
    }

    private boolean hasStudent(Student findStudent) {
        return this.students.stream()
                .anyMatch(student -> student.matchUser(findStudent));
    }

    public Optional<Student> findStudent(Student findStudent) {
        return this.students.stream()
                .filter(student -> student.matchUser(findStudent))
                .findAny();
    }

    public void remove(Student student) {
        boolean hasStudent = hasStudent(student);
        if (!hasStudent) {
            throw new StudentsException("등록된 회원이 아닙니다.");
        }

        this.students.remove(student);
    }
}
