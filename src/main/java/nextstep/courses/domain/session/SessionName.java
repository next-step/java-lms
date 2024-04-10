package nextstep.courses.domain.session;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionName)) {
            return false;
        }
        SessionName that = (SessionName) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
