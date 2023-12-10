package nextstep.courses.domain.session;

public enum SelectStatus {
    SELECTED("선발"),
    UNSELECTED("비선발"),
    UNDECIDED("미정");

    private final String displayName;

    SelectStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
