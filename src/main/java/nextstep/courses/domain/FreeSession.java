package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Students;

import java.time.LocalDateTime;
import java.util.Objects;

public class FreeSession extends Session {
    private final Students students;

    public FreeSession(long id,
                       long creatorId,
                       long courseId,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, dateRange, coverImage, status, creatorId, createdAt, updatedAt);
        this.students = new Students();
    }


    public void register(Student student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeSession that = (FreeSession) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

    public Object[] toFreeParameters() {
        return new Object[]{id};
    }
}
