package nextstep.courses.domain.session;

import nextstep.courses.error.exception.SessionNameEmptyException;

public class SessionName {

    private final String value;

    public SessionName(String value) {
        if (value == null || value.isBlank()) {
            throw new SessionNameEmptyException(value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
