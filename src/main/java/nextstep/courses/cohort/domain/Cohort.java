package nextstep.courses.cohort.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.common.domain.BaseEntity;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;

public class Cohort extends BaseEntity {

    private final Long courseId;
    private int cohortCount;
    private CohortStudentCount cohortStudentCount;
    private CohortState cohortState2;

    public Cohort(
            Long courseId,
            int cohortCount,
            int maxStudentCount,
            int presentStudentCount,
            CohortStateType cohortStateType,
            LocalDateTime registerStartDate,
            LocalDateTime registerEndDate,
            LocalDateTime cohortStartDate,
            LocalDateTime cohortEndDate
    ) {
        this(0L, courseId, cohortCount, maxStudentCount, presentStudentCount,
                cohortStateType, registerStartDate, registerEndDate, cohortStartDate,
                cohortEndDate, null, null);
    }

    public Cohort(
            Long courseId,
            int cohortCount,
            int maxStudentCount,
            int presentStudentCount,
            LocalDateTime registerStartDate,
            LocalDateTime registerEndDate,
            LocalDateTime cohortStartDate,
            LocalDateTime cohortEndDate
    ) {
        this(0L, courseId, cohortCount, maxStudentCount, presentStudentCount,
                CohortStateType.PREPARE, registerStartDate, registerEndDate, cohortStartDate,
                cohortEndDate, null, null);
    }

    public Cohort(
            Long id,
            Long courseId,
            int cohortCount,
            int maxStudentCount,
            int presentStudentCount,
            CohortStateType cohortStateType,
            LocalDateTime registerStartDate,
            LocalDateTime registerEndDate,
            LocalDateTime cohortStartDate,
            LocalDateTime cohortEndDate,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        super(id, createdDate, updatedDate);
        if (courseId <= 0L) {
            throw new IllegalArgumentException("기수는 관련 코스정보가 필수 입니다.");
        }

        if (cohortCount <= 0) {
            throw new IllegalArgumentException("기수는 회차정보가 필수 입니다.");
        }

        this.courseId = courseId;
        this.cohortCount = cohortCount;
        this.cohortStudentCount = new CohortStudentCount(maxStudentCount, presentStudentCount);
        this.cohortState2 = new CohortState(cohortStateType,
                new Period(registerStartDate, registerEndDate),
                new Period(cohortStartDate, cohortEndDate));
    }

    public boolean isCanResist() {
        if (!this.cohortState2.isSameState(CohortStateType.RECRUIT)) {
            return false;
        }

        return this.cohortStudentCount.isNotOverMax();
    }

    public boolean putOnRecruitEnd(LocalDateTime now) {
        if (isNull(now)) {
            return false;
        }

        if (this.cohortState2.isBeforeRecruitPeriod(now)) {
            return false;
        }

        if (!this.cohortState2.isSameState(CohortStateType.RECRUIT)) {
            return false;
        }

        if (this.cohortState2.isInRecruitPeriod(now) && this.cohortStudentCount.isNotOverMax()) {
            return false;
        }

        this.cohortState2.changeRecruitEnd();
        return true;
    }

    public boolean isSameCourseId(Long courseId) {
        if (isNull(courseId)) {
            return false;
        }

        return this.courseId.equals(courseId);
    }

    public void registerStudent() {
        if (!isCanResist()) {
            return;
        }

        this.cohortStudentCount.plusOneCountAtPresent();
    }

    public boolean isCohortStateType(CohortStateType cohortStateType) {
        return this.cohortState2.isSameState(cohortStateType);
    }

    public Long getId() {
        return super.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Cohort cohort = (Cohort) o;
        return cohortCount == cohort.cohortCount && Objects.equals(courseId, cohort.courseId)
                && Objects.equals(cohortStudentCount, cohort.cohortStudentCount)
                && Objects.equals(cohortState2, cohort.cohortState2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId, cohortCount, cohortStudentCount,
                cohortState2);
    }
}
