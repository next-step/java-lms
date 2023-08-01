package nextstep.courses.domain.application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {

  Optional<Application> findById(Long id);

  List<Application> findByCourseId(Long courseId);

  Long save(Application application);
}
