package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final int maxStudents;
    private final List<Student> students;

    public Students(int maxStudents) {
        this.maxStudents = maxStudents;
        this.students  = new ArrayList<>();
    }

    public void addStudent(Student student) throws CannotEnrollException {
        checkAddStudentAble(student);
        students.add(student);
    }

    private void checkAddStudentAble(Student student) throws CannotEnrollException {
        isOverMaxStudent();
        isDuplicated(student);
    }
    private void isOverMaxStudent() throws CannotEnrollException {
        if(maxStudents < students.size() + 1) {
            throw new CannotEnrollException("수강 신청 최대 인원을 초과했습니다.");
        }
    }

    private void isDuplicated(Student student) throws CannotEnrollException {
        if(students.contains(student)) {
            throw new CannotEnrollException("이미 수강 신청 중인 학생입니다.");
        }
    }

}
