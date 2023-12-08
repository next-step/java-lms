package nextstep.tutor.domain;

import java.util.Optional;

public interface TutorRepository {
    Optional<NsTutor> findByTutorId(String tutorId);
}
