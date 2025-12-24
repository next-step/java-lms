package nextstep.courses.cohort.service.repository;

import java.util.Optional;
import nextstep.courses.cohort.domain.Cohort;
import org.springframework.stereotype.Repository;

@Repository
public interface CohortRepository {

    Optional<Cohort> findById(Long id);

    void update(Cohort cohort);
}
