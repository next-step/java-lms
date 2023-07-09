package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ApprovalState {
    PENDING(0),
    APPROVED(1),
    CANCELED(9);

    private int state;

    ApprovalState(int state) {
        this.state = state;
    }

    public static ApprovalState of(int state) {
        return (ApprovalState) Arrays.stream(ApprovalState.values())
                .filter(approvalState -> approvalState.getInt() == state)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProgressState value: " + state));
    }

    public int getInt() {
        return state;
    }
}
