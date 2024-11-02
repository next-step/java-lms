package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.RecruitmentClosedException;

import java.time.LocalDateTime;
import java.util.List;

public class TobeFreeSession extends TobeSession {

    public TobeFreeSession(long id,
                           long courseId,
                           DateRange dateRange,
                           List<TobeCoverImage> coverImages,
                           Instructor instructor,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id, courseId, Category.FREE, dateRange, coverImages, instructor, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }
    public TobeFreeSession(long id,
                           long courseId,
                           DateRange dateRange,
                           List<TobeCoverImage> coverImages,
                           long instructorId,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id, courseId, Category.FREE, dateRange, coverImages, instructorId, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }

    public void register(TobeStudent student) {
        if (RecruitmentStatus.CLOSED.equals(recruitmentStatus)) {
            throw new RecruitmentClosedException(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
        }
        students.add(student);
    }

    @Override
    public String toString() {
        return "TobeFreeSession{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", category=" + category +
                ", dateRange=" + dateRange +
                ", coverImages=" + coverImages +
                ", instructorId=" + instructorId +
                ", processStatus=" + processStatus +
                ", recruitmentStatus=" + recruitmentStatus +
                ", students=" + students +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}