package nextstep.courses.domain.student;

public enum ApprovalState {
    APPROVAL("승인"),
    NON_APPROVAL("미승인");

    private final String value;

    ApprovalState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ApprovalState{" +
            "value='" + value + '\'' +
            "} " + super.toString();
    }
}
