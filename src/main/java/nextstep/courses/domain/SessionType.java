package nextstep.courses.domain;

import nextstep.courses.exception.NoSuchSessionException;

public enum SessionType {
    FREE, PAY;

    public static SessionType from(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoSuchSessionException(
                    String.format("%s는 존재하지 않는 강의 타입입니다.", value)
            );
        }
    }
}
