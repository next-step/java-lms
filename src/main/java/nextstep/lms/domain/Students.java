package nextstep.lms.domain;

import nextstep.lms.enums.StudentStatusEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Students {
    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public int size() {
        return students.size();
    }

    public void enroll(Student student) {
        duplicationCheck(student);
        this.students.add(student);
    }

    public void capacityCheck(int capacity) {
        if (selectedStudentCount() >= capacity) {
            throw new IllegalArgumentException("수강생이 모두 선발됐습니다.");
        }
    }

    private void duplicationCheck(Student student) {
        if (this.students.contains(student)) {
            throw new IllegalArgumentException("이미 수강중인 강의입니다.");
        }
    }

    private int selectedStudentCount() {
        return (int) students.stream()
                .filter(student -> student.isSelected())
                .count();
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public Student selection(Student student) {
        Optional<Student> updatedStudent = changeStudentStatus(student, StudentStatusEnum.SELECTED);
        if (updatedStudent.isEmpty()) {
            throw new IllegalArgumentException("강의 신청자가 아닙니다.");
        }
        return updatedStudent.get();
    }

    public Student nonSelection(Student student) {
        Optional<Student> updatedStudent = changeStudentStatus(student, StudentStatusEnum.NON_SELECTED);
        if (updatedStudent.isEmpty()) {
            throw new IllegalArgumentException("강의 신청자가 아닙니다.");
        }
        return updatedStudent.get();
    }

    private Optional<Student> changeStudentStatus(Student student, StudentStatusEnum studentStatusEnum) {
        return this.students.stream()
                .filter(appliedStudent -> appliedStudent.getUserId() == student.getUserId())
                .peek(appliedStudent -> appliedStudent.setStatus(studentStatusEnum))
                .findFirst();
    }
}
