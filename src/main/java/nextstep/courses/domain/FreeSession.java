package nextstep.courses.domain;

import nextstep.courses.domain.session.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.session.Category.FREE;

public class FreeSession extends Session {
    private final Students students;

    public FreeSession(long id,
                       long courseId,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, FREE, dateRange, coverImage, status, creatorId, createdAt, updatedAt);
        this.students = new Students();
    }

    public FreeSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, category, dateRange, coverImage, status, creatorId, createdAt, updatedAt);
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
}