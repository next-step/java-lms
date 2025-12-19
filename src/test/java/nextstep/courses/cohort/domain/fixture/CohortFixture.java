package nextstep.courses.cohort.domain.fixture;

import java.time.LocalDateTime;
import nextstep.courses.cohort.domain.Cohort;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;

public class CohortFixture {

    public static Cohort 식별자를_전달받아_기수픽스처를_생성한다(Long id) {
        LocalDateTime registerStartDate = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        LocalDateTime registerEndDate = LocalDateTime.of(2025, 1, 2, 0, 0, 0);

        LocalDateTime cohortStartDate = LocalDateTime.of(2025, 1, 3, 0, 0, 0);
        LocalDateTime cohortEndDate = LocalDateTime.of(2025, 1, 4, 0, 0, 0);

        LocalDateTime fixDateTime = LocalDateTime.of(2025, 1, 5, 0, 0, 0);

        return new Cohort(
                id, 2L, 5, 1, 0, CohortStateType.PREPARE,
                registerStartDate,
                registerEndDate,
                cohortStartDate,
                cohortEndDate,
                fixDateTime,
                fixDateTime
        );
    }

    public static Cohort 식별자와_상태를_전달받아_기수픽스처를_생성한다(Long id, CohortStateType cohortStateType) {
        LocalDateTime registerStartDate = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        LocalDateTime registerEndDate = LocalDateTime.of(2025, 1, 2, 0, 0, 0);

        LocalDateTime cohortStartDate = LocalDateTime.of(2025, 1, 3, 0, 0, 0);
        LocalDateTime cohortEndDate = LocalDateTime.of(2025, 1, 4, 0, 0, 0);

        LocalDateTime fixDateTime = LocalDateTime.of(2025, 1, 5, 0, 0, 0);

        return new Cohort(
                id, 2L, 5, 1, 0, cohortStateType,
                registerStartDate,
                registerEndDate,
                cohortStartDate,
                cohortEndDate,
                fixDateTime,
                fixDateTime
        );
    }
}
