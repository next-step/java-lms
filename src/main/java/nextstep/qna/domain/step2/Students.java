package nextstep.qna.domain.step2;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> students = new ArrayList<>();

    public Students(List<Student> students) {
        this.students = students;
    }

    public Student add(Student student) {
        students.stream()
                .filter(s -> s.equals(student))
                .findAny()
                .ifPresent(s -> {
                    throw new IllegalArgumentException("이미 존재하는 학생입니다.");
                });
        students.add(student);
        return student;
    }

    public int size() {
        return students.size();
    }

}
