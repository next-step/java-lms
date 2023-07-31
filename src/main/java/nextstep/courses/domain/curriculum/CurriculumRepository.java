package nextstep.courses.domain.curriculum;

import java.util.List;
import java.util.Optional;

public interface CurriculumRepository {

  Optional<Curriculum> findById(Long id);

  List<Curriculum> findByBatchId(Long batchId);

  Long save(Curriculum curriculum);
}
