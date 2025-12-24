package nextstep.courses.cohort.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;

public class CohortState {
    private CohortStateType cohortStateType;
    private Period registerPeriod;
    private Period cohortPeriod;

    public CohortState(CohortStateType cohortStateType, Period registerPeriod, Period cohortPeriod) {
        this.cohortStateType = cohortStateType;
        this.registerPeriod = registerPeriod;
        this.cohortPeriod = cohortPeriod;
    }

    public boolean isSameState(CohortStateType cohortStateType) {
        if (isNull(cohortStateType)) {
            return false;
        }

        return this.cohortStateType.equals(cohortStateType);
    }

    public boolean isInRecruitPeriod(LocalDateTime now) {
        return this.registerPeriod.isPeriodIn(now);
    }

    public boolean isBeforeRecruitPeriod(LocalDateTime now) {
        return this.registerPeriod.isBeforeStartDate(now);
    }

    public void changeRecruitEnd() {
        this.cohortStateType = CohortStateType.RECRUIT_END;
    }
}
