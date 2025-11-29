package nextstep.courses.domain.session.constant;

public enum SessionType {
    FREE, PAID;

    public static SessionType from(String value) {
        for (SessionType status : SessionType.values()) {
            if (value.equals(status.name())) {
                return status;
            }
        }
        throw new IllegalArgumentException("강의는 무료 강의와 유료 강의만 개설 가능합니다.");
    }
}
