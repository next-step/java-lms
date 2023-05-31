package nextstep.courses.domain;

import java.util.List;
import java.util.Optional;

public interface EnrollRepository {
    Enroll save(Enroll enroll);

    Optional<Enroll> findById(EnrollId enrollId);

    List<Enroll> findAll();

    void deleteAll();
}
