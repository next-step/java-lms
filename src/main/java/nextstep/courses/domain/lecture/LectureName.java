package nextstep.courses.domain.lecture;

import nextstep.courses.error.exception.LectureNameEmptyException;

public class LectureName {

    private final String value;

    public LectureName(String value) {
        if (value == null || value.isBlank()) {
            throw new LectureNameEmptyException(value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
