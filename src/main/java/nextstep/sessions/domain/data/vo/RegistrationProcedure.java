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
        return selectionType.isBeforeSelection();
    }

    public boolean isSelected() {
        return selectionType.isSelected();
    }

    public boolean isApproved() {
        return approvalType.isApproved();
    }
}
