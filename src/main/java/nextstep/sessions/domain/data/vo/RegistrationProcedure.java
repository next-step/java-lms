package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;

public class RegistrationProcedure {

    private final SelectionType selectionType;
    private final ApprovalType approvalType;

    public RegistrationProcedure(SelectionType selectionType, ApprovalType approvalType) {
        this.selectionType = selectionType;
        this.approvalType = approvalType;
    }

    public SelectionType selectionType() {
        return selectionType;
    }

    public ApprovalType approvalType() {
        return approvalType;
    }

    public boolean isBeforeSelection() {
        return selectionType == SelectionType.BEFORE_SELECTION;
    }

    public boolean isSelected() {
        return selectionType == SelectionType.SELECTION;
    }

    public boolean isApproved() {
        return approvalType == ApprovalType.APPROVAL;
    }
}
