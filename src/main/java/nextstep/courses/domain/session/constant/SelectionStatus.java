package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum SelectionStatus {
    PENDING("선발 대기"),
    SELECTED("선발"),
    NOT_SELECTED("미선발");

    private final String value;

    SelectionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SelectionStatus from(String name) {
        return Arrays.stream(SelectionStatus.values())
                .filter(status -> status.matchStatus(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상태명을 잘못 입력하였습니다."));
    }

    private boolean matchStatus(String value) {
        return this.value.equals(value);
    }

    public boolean isSelected() {
        return this.equals(SELECTED);
    }

    public boolean isNotSelected() {
        return this.equals(NOT_SELECTED);
    }
}
