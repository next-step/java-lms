package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final List<Student> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public void add(NsUser student) {
        validate(student);

        this.students.add(new Student(student));
    }

    public void approve(Student approveStudent) {
        findStudent(approveStudent).approve();
    }

    public void refuse(Student refuseStudent) {
        findStudent(refuseStudent).refuse();
    }

    public int approvalStudentsCount() {
        return (int) this.students.stream()
                .filter(Student::isApproval)
                .count();
    }

    public int applyStudentsCount() {
        return this.students.size();
    }

    private Student findStudent(Student findStudent) {
        return this.students.stream()
                .filter(student -> student.equals(findStudent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));
    }

    private void validate(NsUser student) {
        if (isAlreadyStudent(student)) {
            throw new IllegalArgumentException("이미 수강신청을 완료한 학생입니다.");
        }
    }

    public boolean isAlreadyStudent(NsUser student) {
        return this.students.contains(new Student(student));
    }
}
