package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionCostType {
    FREE("무료"),
    PAID("유료");

    private final String sessionCostType;
    SessionCostType(String sessionCostType) {
        this.sessionCostType = sessionCostType;
    }

    public static SessionCostType of(String sessionCostType) {
        return Arrays.stream(values())
                .filter(costType -> costType.isMatch(sessionCostType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 강의비용 유형 입니다."));
    }

    private boolean isMatch(String sessionCostType) {
        return this.sessionCostType.equals(sessionCostType);
    }
}
