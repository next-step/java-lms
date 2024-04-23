package nextstep.courses.domain.enrollment;

import nextstep.courses.error.exception.SessionNameEmptyException;

public class StudentName {

    private final String value;

    public StudentName(String value) {
        if (value == null || value.isBlank()) {
            throw new SessionNameEmptyException(value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
