package nextstep.courses.domain.session.student;

import java.util.Objects;

import static nextstep.courses.domain.session.student.SelectionStatus.*;

public class Student {

    private Long id;
    private Long enrolmentId;
    private Long nsUserId;
    private SelectionStatus selectionStatus;

    public Student(Long enrolmentId, Long nsUserId, SelectionStatus selectionStatus) {
        this.enrolmentId = enrolmentId;
        this.nsUserId = nsUserId;
        this.selectionStatus = selectionStatus;
    }

    public Student(Long id, Long enrolmentId, Long nsUserId, SelectionStatus selectionStatus) {
        this(enrolmentId, nsUserId, selectionStatus);
        this.id = id;
    }

    public Long getEnrolmentId() {
        return this.enrolmentId;
    }

    public Long getNsUserId() {
        return this.nsUserId;
    }

    public SelectionStatus getSelectionStatus() {
        return this.selectionStatus;
    }

    public void changeStatus(SelectionStatus selectionStatus) {
        validateSelectionStatus();

        this.selectionStatus = selectionStatus;
    }

    private void validateSelectionStatus() {
        if (WAITING.equals(selectionStatus)) {
            throw new IllegalArgumentException("수강생 선별은 승인 또는 거절 중 하나만 선택할 수 있습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(enrolmentId, student.enrolmentId) && Objects.equals(nsUserId, student.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enrolmentId, nsUserId);
    }
}
