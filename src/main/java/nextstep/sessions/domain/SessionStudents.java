package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionStudents {

    private Set<SessionStudent> students = new HashSet<>();

    public SessionStudents() {
        this.students = new HashSet<>();
    }

    public SessionStudents(List<SessionStudent> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            checkStudent(students.get(i), students.get(i + 1));
        }
        this.students = new HashSet<>(students);
    }

    public Set<SessionStudent> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void addStudent(SessionStudent student) {
        for (SessionStudent sessionStudent : students) {
            checkStudent(sessionStudent, student);
        }
        students.add(student);
    }

    private void checkStudent(SessionStudent student1, SessionStudent student2) {
        if (student1.equals(student2)) {
            throw new IllegalArgumentException("이미 수강신청한 학생입니다.");
        }
    }

    public int approvalStudentCount() {
        return (int) students.stream()
                .filter(student -> student.isApproval())
                .count();
    }

    public void approval(SessionStudent sessionStudent, NsUser loginUser) {
        if (!loginUser.isAdminUser()) {
            throw new IllegalStateException("강사만 승인할 수 있습니다.");
        }
        SessionStudent findStudent = students.stream()
                .filter(student -> student.equals(student))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("%s 수강신청 내역이 없는 학생입니다.", sessionStudent.getUser().getName())));
        findStudent.approval();
    }

    public void cancel(SessionStudent sessionStudent, NsUser loginUser) {
        if (!loginUser.isAdminUser()) {
            throw new IllegalStateException("강사만 취소할 수 있습니다.");
        }
        SessionStudent findStudent = students.stream()
                .filter(student -> student.equals(sessionStudent))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("%s 수강신청 내역이 없는 학생입니다.", sessionStudent.getUser().getName())));
        findStudent.cancel();
        this.students = students.stream()
                .filter(student -> !student.equals(sessionStudent))
                .collect(Collectors.toSet());
    }
}
