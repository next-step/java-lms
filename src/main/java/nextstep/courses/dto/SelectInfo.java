package nextstep.courses.dto;

import nextstep.courses.domain.session.enums.SelectionStatus;

public class SelectInfo {

    private Long sessionId;
    private Long studentId;
    private SelectionStatus selectionStatus;

    public SelectInfo(Long sessionId, Long studentId, SelectionStatus selectionStatus) {
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.selectionStatus = selectionStatus;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public SelectionStatus getSelectionStatus() {
        return selectionStatus;
    }
}
