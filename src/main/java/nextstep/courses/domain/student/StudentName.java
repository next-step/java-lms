package nextstep.courses.domain.student;

import nextstep.courses.error.exception.StudentNameEmptyException;

public class StudentName {

    private final String value;

    public StudentName(String value) {
        if (value == null || value.isBlank()) {
            throw new StudentNameEmptyException(value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
