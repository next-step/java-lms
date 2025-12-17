package nextstep.courses.cohort.domain;

import java.time.LocalDateTime;
import nextstep.common.domain.BaseEntity;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;

public class Cohort extends BaseEntity {

    private final Long courseId;
    private CohortStateType cohortStateType;
    private Period registerPeriod;
    private Period cohortPeriod;

    public Cohort(
            Long courseId,
            LocalDateTime registerStartDate,
            LocalDateTime registerEndDate,
            LocalDateTime cohortStartDate,
            LocalDateTime cohortEndDate
    ) {
        this(0L, courseId, CohortStateType.PREPARE, registerStartDate, registerEndDate, cohortStartDate, cohortEndDate, null, null);
    }

    public Cohort(
            Long id,
            Long courseId,
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

        this.courseId = courseId;
        this.cohortStateType = cohortStateType;
        this.registerPeriod = new Period(registerStartDate, registerEndDate);
        this.cohortPeriod = new Period(cohortStartDate, cohortEndDate);
    }
}
