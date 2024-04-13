package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {

    FREE("FREE"),
    PAID("PAID");

    private final String type;

    SessionType(String type) {
        this.type = type;
    }

    public boolean contains(String type) {
        return Arrays.stream(values())
                .map(SessionType::getType)
                .anyMatch(sessionType -> sessionType.equals(type));
    }

    private String getType() {
        return type;
    }

}
