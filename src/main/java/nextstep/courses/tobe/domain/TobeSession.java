package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.ProcessEndedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static nextstep.courses.tobe.domain.ProcessStatus.*;
import static nextstep.courses.tobe.domain.RecruitmentStatus.*;

public abstract class TobeSession {
    public static final String NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE = "종료된 강의를 모집중 상태로 바꿀수 없습니다.";
    public static final String NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE = "닫힌 강의는 수강신청할 수 없습니다.";
    protected final long id;
    protected final long courseId;
    protected final Category category;
    protected final DateRange dateRange;
    protected final List<TobeCoverImage> coverImages;
    protected final long instructorId;
    protected final ProcessStatus processStatus;
    protected final RecruitmentStatus recruitmentStatus;
    protected final long creatorId;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public TobeSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       List<TobeCoverImage> coverImages,
                       Instructor instructor,
                       ProcessStatus processStatus,
                       RecruitmentStatus recruitmentStatus,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        if (isInvalidProcess(processStatus, recruitmentStatus)) {
            throw new ProcessEndedException(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
        }
        this.id = id;
        this.category = category;
        this.courseId = courseId;
        this.dateRange = dateRange;
        this.coverImages = new ArrayList<>(coverImages);
        this.instructorId = instructor.getId();
        this.processStatus = processStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    private static boolean isInvalidProcess(ProcessStatus processStatus, RecruitmentStatus recruitmentStatus) {
        return ENDED.equals(processStatus) &&
                OPEN.equals(recruitmentStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeSession that = (TobeSession) o;
        return id == that.id && courseId == that.courseId && instructorId == that.instructorId && creatorId == that.creatorId && category == that.category && Objects.equals(dateRange, that.dateRange) && Objects.equals(coverImages, that.coverImages) && processStatus == that.processStatus && recruitmentStatus == that.recruitmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, category, dateRange, coverImages, instructorId, processStatus, recruitmentStatus, creatorId);
    }
}
