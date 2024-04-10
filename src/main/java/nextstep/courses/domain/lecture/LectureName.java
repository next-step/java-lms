package nextstep.courses.domain.lecture;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LectureName)) {
            return false;
        }
        LectureName that = (LectureName) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
