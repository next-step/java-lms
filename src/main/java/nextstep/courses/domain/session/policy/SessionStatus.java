package nextstep.courses.domain.session.policy;

import lombok.Getter;

@Getter
public enum SessionStatus {
    PREPARING("preparing"),
    ONGOING("ongoing"),
    CLOSED("closed");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnroll() {
        return this == ONGOING;
    }

    public static SessionStatus fromString(String status) {
        for (SessionStatus s : values()) {
            if (s.status.equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException(String.format("'%s'은(는) 유효한 세션 상태가 아닙니다.", status));
    }
}
