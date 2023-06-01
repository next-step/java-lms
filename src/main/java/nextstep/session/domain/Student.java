package nextstep.session.domain;

import nextstep.session.StudentNotPassedException;
import nextstep.session.StudentNumberExceededException;
import nextstep.students.domain.Students;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private Long maxNumberOfStudent;
    private final List<Students> students = new ArrayList<>();
    private final List<PassStudent> passStudents = new ArrayList<>();

    public Student(Long maxNumberOfStudent) {
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public Long getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void signUp(Students student) throws StudentNumberExceededException {
        if (isFullStudents()) {
            throw new StudentNumberExceededException("최대 수강 인원 수를 초과했습니다.");
        }

        if (!isPassedStudent(student)) {
            throw new StudentNotPassedException("선발된 학생만 수강신청 가능합니다.");
        }

        this.students.add(student);
    }

    private boolean isFullStudents() {
        return students.size() == maxNumberOfStudent;
    }

    private boolean isPassedStudent(Students student) {
        return passStudents.stream()
                .filter(passStudent -> student.getNsUserId().equals(passStudent.getNsUserId()))
                .anyMatch(PassStudent::getPass);
    }
}
