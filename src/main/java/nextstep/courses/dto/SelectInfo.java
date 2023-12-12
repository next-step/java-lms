package nextstep.courses.dto;

import nextstep.courses.domain.session.student.SelectionStatus;

public class SelectInfo {

    private Long teacherId;
    private Long studentId;
    private SelectionStatus selectionStatus;

    public SelectInfo(Long teacherId, Long studentId, SelectionStatus selectionStatus) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.selectionStatus = selectionStatus;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public SelectionStatus getEnrollStatus() {
        return selectionStatus;
    }
}
