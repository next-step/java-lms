package nextstep.courses.domain.session.student;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {

    private List<SessionStudent> students;

    public SessionStudents() {
        this.students = new ArrayList<>();
    }

    public SessionStudents(List<SessionStudent> students) {
        this.students = students;
    }

    public boolean add(SessionStudent student) {
        validateDuplicate(student);

        return this.students.add(student);
    }

    private void validateDuplicate(SessionStudent student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("이미 해당 강의를 수강 중 입니다.");
        }
    }

    public int size() {
        return this.students.size();
    }

    public SessionStudent selectStudents(SessionStudent waitingStudent, SelectionStatus selectionStatus) {
        return students.stream()
            .filter(student -> student.equals(waitingStudent))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("수강생이 존재하지 않습니다."))
            .changeStatus(selectionStatus);
    }
}
