package nextstep.courses.domain.session;

import nextstep.courses.exception.NoSuchSessionException;

public enum SessionStatus {
    READY, OPEN, CLOSE;

    public static SessionStatus from(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoSuchSessionException(
                    String.format("%s는 존재하지 않는 강의 상태입니다.", value)
            );
        }
    }

    public boolean isReady() {
        return this.equals(SessionStatus.READY);
    }

    public boolean isSame(SessionStatus status) {
        return this == status;
    }
}
