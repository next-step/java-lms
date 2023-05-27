package nextstep.courses.domain;

import java.util.List;

public class Students {
    private final Session session;
    private final List<Student> students;

    public Students(Session session, List<Student> students) {
        this.session = session;
        this.students = students;
    }

    public void add(Student student) {
        validateToAdd(student);

        students.add(student);
    }

    public int size() {
        return students.size();
    }

    public void validateToAdd(Student student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("이미 등록된 학생입니다.");
        }
    }
}

