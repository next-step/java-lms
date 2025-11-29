package nextstep.courses.domain.session.constant;

public enum SessionStatus {
    PENDING,
    ACTIVE,
    FINISHED;

    public static SessionStatus from(String value) {
        for (SessionStatus status : SessionStatus.values()) {
            if (value.equals(status.name())) {
                return status;
            }
        }
        throw new IllegalArgumentException("준비중, 모집중, 종료 3가지 상태로만 생성 가능합니다.");
    }

}
