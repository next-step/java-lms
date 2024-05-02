package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            throw new StudentAlreadyEnrolledException("이미 수강신청 한 학생입니다");
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

    public void approval(Student student) {
        Student targetStudent = students.stream()
            .filter(enrolledStudent -> enrolledStudent.equals(student)
            ).findFirst().orElseThrow(() -> new IllegalArgumentException("수강신청하지 않은 학생입니다."));
        targetStudent.approval();
    }

    public void cancel(Student student) {
        Student targetStudent = students.stream()
            .filter(enrolledStudent -> enrolledStudent.equals(student)
            ).findFirst().orElseThrow(() -> new IllegalArgumentException("수강신청하지 않은 학생입니다."));
        targetStudent.cancel();
    }

    public List<Student> getApprovedStudents() {
        return students.stream().filter(enrolledStudent -> enrolledStudent.isApproved()).collect(
            Collectors.toUnmodifiableList());
    }


}
