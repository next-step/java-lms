package nextstep.courses.dto;

import nextstep.courses.domain.session.student.SelectionStatus;

public class SelectInfo {

    private Long studentId;
    private Long sessionId;
    private SelectionStatus selectionStatus;

    public SelectInfo(Long studentId, Long sessionId, SelectionStatus selectionStatus) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.selectionStatus = selectionStatus;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public SelectionStatus getSelectionStatus() {
        return selectionStatus;
    }
}
