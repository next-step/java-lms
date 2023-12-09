package nextstep.sessions.domain.data.registration;

public class RegistrationProcedure {

    private SelectionType selectionType;
    private ApprovalType approvalType;

    public RegistrationProcedure(SelectionType selectionType, ApprovalType approvalType) {
        this.selectionType = selectionType;
        this.approvalType = approvalType;
    }

    void select() {
        this.selectionType = SelectionType.SELECTION;
    }

    public void approve() {
        this.approvalType = ApprovalType.APPROVAL;
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
