package nextstep.courses.domain;

import nextstep.courses.RecruitmentClosedException;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    public FreeSession(long id,
                       long courseId,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       List<CoverImage> coverImages,
                       Instructor instructor,
                       ProcessStatus processStatus,
                       RecruitmentStatus recruitmentStatus,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, Category.FREE, dateRange, coverImage, status, coverImages, instructor, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }

    public FreeSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       long instructorId,
                       ProcessStatus processStatus,
                       RecruitmentStatus recruitmentStatus,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, category, dateRange, coverImage, status, List.of(), instructorId, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }


    public void register(Student student) {
        if (RecruitmentStatus.CLOSED.equals(recruitmentStatus)) {
            throw new RecruitmentClosedException(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
        }
        students.add(student);
    }

    @Override
    public String toString() {
        return "FreeSession{" +
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