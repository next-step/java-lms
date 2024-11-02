package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.ProcessEndedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class TobeSession {
    public static final String NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE = "종료된 강의를 모집중 상태로 바꿀수 없습니다.";
    public static final String NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE = "닫힌 강의는 수강신청할 수 없습니다.";
    protected final long id;
    protected final long courseId;
    protected final Category category;
    protected final DateRange dateRange;
    protected final TobeCoverImage coverImage;
    protected final ProcessStatus processStatus;
    protected final RecruitmentStatus recruitmentStatus;
    protected final long creatorId;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public TobeSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       TobeCoverImage coverImage,
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
        this.coverImage = coverImage;
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
        return ProcessStatus.ENDED.equals(processStatus) &&
                RecruitmentStatus.OPEN.equals(recruitmentStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeSession that = (TobeSession) o;
        return id == that.id && courseId == that.courseId && creatorId == that.creatorId && category == that.category && Objects.equals(dateRange, that.dateRange) && Objects.equals(coverImage, that.coverImage) && processStatus == that.processStatus && recruitmentStatus == that.recruitmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, category, dateRange, coverImage, processStatus, recruitmentStatus, creatorId);
    }
}
