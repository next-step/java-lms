package nextstep.sessions.domain;

public enum SessionSelectionStatus {
    SELECTED,
    NOT_SELECTED;

    public boolean isNotSelectable() {
        return this != SELECTED;
    }
}
