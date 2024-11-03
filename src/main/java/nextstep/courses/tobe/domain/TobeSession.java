package nextstep.courses.tobe.domain;

import nextstep.courses.domain.Instructor;
import nextstep.courses.domain.ProcessStatus;
import nextstep.courses.domain.RecruitmentStatus;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.NotMatchedInstructorException;
import nextstep.courses.tobe.ProcessEndedException;
import nextstep.courses.tobe.domain.session.TobeCoverImages;
import nextstep.courses.tobe.domain.session.TobeStudents;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static nextstep.courses.domain.ProcessStatus.ENDED;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;

public abstract class TobeSession {
    public static final String NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE = "종료된 강의를 모집중 상태로 바꿀수 없습니다.";
    public static final String NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE = "닫힌 강의는 수강신청할 수 없습니다.";
    public static final String NO_AUTH_INSTRUCTOR_TO_UPDATE_APPROVE_STATUS_MESSAGE = "승인 권한이 없는 강사입니다.";
    public static final String NO_AUTH_INSTRUCTOR_TO_UPDATE_DENIED_STATUS_MESSAGE = "승인취소 권한이 없는 강사입니다.";

    protected final long id;
    protected final long courseId;
    protected final Category category;
    protected final DateRange dateRange;
    protected final TobeCoverImages coverImages;
    protected final long instructorId;
    protected final ProcessStatus processStatus;
    protected final RecruitmentStatus recruitmentStatus;
    protected final TobeStudents students;
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
        this(id, courseId, category, dateRange, coverImages, instructor.getId(), processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }
    public TobeSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       List<TobeCoverImage> coverImages,
                       long instructorId,
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
        this.coverImages = new TobeCoverImages(coverImages);
        this.instructorId = instructorId;
        this.processStatus = processStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.students = new TobeStudents();
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void approveAll(Instructor instructor) {
        if (instructorId != instructor.getId()) {
            throw new NotMatchedInstructorException(NO_AUTH_INSTRUCTOR_TO_UPDATE_APPROVE_STATUS_MESSAGE);
        }

        this.students.each(TobeStudent::approved);
    }

    public void deniedAll(Instructor instructor) {
        if (instructorId != instructor.getId()) {
            throw new NotMatchedInstructorException(NO_AUTH_INSTRUCTOR_TO_UPDATE_DENIED_STATUS_MESSAGE);
        }

        this.students.each(TobeStudent::denied);
    }

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public Category getCategory() {
        return category;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
        return id == that.id && courseId == that.courseId && instructorId == that.instructorId && creatorId == that.creatorId && category == that.category && Objects.equals(dateRange, that.dateRange) && Objects.equals(coverImages, that.coverImages) && processStatus == that.processStatus && recruitmentStatus == that.recruitmentStatus && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, category, dateRange, coverImages, instructorId, processStatus, recruitmentStatus, students, creatorId);
    }
}
