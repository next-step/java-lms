package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.RecruitmentClosedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TobeFreeSession extends TobeSession {
    private final List<TobeStudent> students;
    public TobeFreeSession(long id,
                           long courseId,
                           DateRange dateRange,
                           TobeCoverImage coverImage,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id, courseId, Category.FREE, dateRange, coverImage, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
        this.students = new ArrayList<>();
    }

    public void register(TobeStudent student) {
        if (RecruitmentStatus.CLOSED.equals(recruitmentStatus)) {
            throw new RecruitmentClosedException(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
        }
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TobeFreeSession that = (TobeFreeSession) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), students);
    }
}
