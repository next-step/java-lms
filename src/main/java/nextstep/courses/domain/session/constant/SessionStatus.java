package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum SessionStatus {
    PENDING,
    ACTIVE,
    FINISHED;

    public static SessionStatus from(String value) {
        return Arrays.stream(SessionStatus.values())
                .filter(status -> status.matchStatus(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("준비중, 진행중, 종료 3가지 상태로만 생성 가능합니다."));
    }

    private boolean matchStatus(String value) {
        return this.name().equals(value);
    }

}
