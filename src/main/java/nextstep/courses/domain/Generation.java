package nextstep.courses.domain;

import java.util.Objects;

public class Generation {
    private final Long generationNo;
    private final Long courseId;

    public Generation(Long generationNo, Long courseId) {
        this.generationNo = generationNo;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Generation that = (Generation) o;
        return Objects.equals(generationNo, that.generationNo) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generationNo, courseId);
    }
}
