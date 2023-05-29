package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        if (students.contains(student)) {
            throw new DuplicateStudentException("이미 등록된 학생ID입니다.");
        }

        students.add(student);
    }

    public int getSize() {
        return students.size();
    }
}
