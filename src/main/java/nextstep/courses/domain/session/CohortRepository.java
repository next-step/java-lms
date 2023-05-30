package nextstep.courses.domain.session;

import nextstep.courses.domain.Cohort;

public interface CohortRepository {

    int save(Cohort cohort);

    Cohort findById(Long id);

    int update(Cohort cohort);

    int delete(Cohort cohort);

}
