package nextstep.courses.cohort.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.common.domain.BaseEntity;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;

public class Cohort extends BaseEntity {

    private final Long courseId;
    private int cohortCount;
    private int maxStudentCount;
    private int presentStudentCount;
    private CohortStateType cohortStateType;
    private Period registerPeriod;
    private Period cohortPeriod;

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
        this(0L, courseId, cohortCount, maxStudentCount, presentStudentCount, CohortStateType.PREPARE, registerStartDate, registerEndDate, cohortStartDate, cohortEndDate, null, null);
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
        this.maxStudentCount = maxStudentCount;
        this.presentStudentCount = presentStudentCount;
        this.cohortStateType = cohortStateType;
        this.registerPeriod = new Period(registerStartDate, registerEndDate);
        this.cohortPeriod = new Period(cohortStartDate, cohortEndDate);
    }

    public boolean isCanResist() {
        if (!this.cohortStateType.equals(CohortStateType.PREPARE)) {
            return false;
        }

        return this.maxStudentCount > this.presentStudentCount;
    }

    public boolean isInRecruitBy(LocalDateTime now) {
        // 이것도 수강신청 정책 관련 로직일듯
        return this.registerPeriod.isPeriodIn(now);
    }

    public boolean isInActiveBy(LocalDateTime now) {
        // 이것도 수강신청 정책 관련 로직일듯
        return this.cohortPeriod.isPeriodIn(now);
    }

    public void putOnRecruit(LocalDateTime now) {
        if (!isInRecruitBy(now)) {
            throw new IllegalArgumentException("현재는 수강신청 기간이 아닙니다");
        }

        this.cohortStateType = CohortStateType.RECRUIT;
    }

    public void putOnActive(LocalDateTime now) {
        if (!isInActiveBy(now)) {
            throw new IllegalArgumentException("현재는 수강중 기간이 아닙니다");
        }

        this.cohortStateType = CohortStateType.ACTIVE;
    }

    public boolean isSameCohortId(Long cohortId) {
        if (isNull(cohortId)) {
            return false;
        }

        return super.getId().equals(cohortId);
    }

    public CohortStateType cohortStateType() {
        return this.cohortStateType;
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
        return cohortCount == cohort.cohortCount && maxStudentCount == cohort.maxStudentCount
                && presentStudentCount == cohort.presentStudentCount && Objects.equals(
                courseId, cohort.courseId) && cohortStateType == cohort.cohortStateType
                && Objects.equals(registerPeriod, cohort.registerPeriod)
                && Objects.equals(cohortPeriod, cohort.cohortPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId, cohortCount, maxStudentCount,
                presentStudentCount, cohortStateType, registerPeriod, cohortPeriod);
    }
}
