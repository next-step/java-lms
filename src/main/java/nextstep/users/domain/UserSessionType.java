package nextstep.users.domain;

import nextstep.courses.domain.session.SessionType;

public enum UserSessionType {
    FREE, PAY;

    boolean isSameType(SessionType type) {
        return this.toString().equals(type.toString());
    }
}
