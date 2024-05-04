package nextstep.sessions.domain;

public enum SessionSelectionStatus {
    SELECTED,
    NOT_SELECTED;

    public boolean isSelectable() {
        return this == SELECTED;
    }
}
