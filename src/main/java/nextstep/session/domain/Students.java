package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.session.StudentAlreadyEnrolledException;

public class Students {

    private final List<Student> students;


    public Students() {
        this.students = new ArrayList<>();
    }

    public Students(List<Student> list) {
        this.students = list;
    }

    public void add(Student student) {
        if (isAlreadyEnrolled(student)) {
            throw new StudentAlreadyEnrolledException("이미 등록된 학생입니다");
        }
        students.add(student);
    }

    public boolean isAlreadyEnrolled(Student student) {
        return students.stream().anyMatch(enrolledStudent -> enrolledStudent.equals(student)
        );
    }

    public int enrolledStudentCount() {
        return students.size();
    }

}
