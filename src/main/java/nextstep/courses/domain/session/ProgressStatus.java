package nextstep.courses.domain.session;

import java.util.Arrays;

public enum ProgressStatus {
    PREPARING("준비중"),
    IN_PROGRESS("진행중"),
    CLOSED("종료");

    private final String value;

    ProgressStatus(String value) {
        this.value = value;
    }

    public static ProgressStatus from(String description) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 진행 상태입니다: " + description));
    }

    public String getValue() {
        return value;
    }
}
