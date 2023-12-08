package nextstep.courses.domain;

public enum SelectionStatus {
    SELECTED, NOT_SELECTED;

    public boolean isSelected() {
        return this == SELECTED;
    }
}
