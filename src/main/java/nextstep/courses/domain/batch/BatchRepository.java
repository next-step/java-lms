package nextstep.courses.domain.batch;

import java.util.List;
import java.util.Optional;

public interface BatchRepository {

  Optional<Batch> findById(Long id);

  List<Batch> findByCourseId(Long courseId);

  Long save(Batch batch);
}
