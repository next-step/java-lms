package nextstep.courses.infrastructure;

import java.util.Optional;
import nextstep.courses.entity.StudentEntity;

public interface StudentRepository {

    int save(StudentEntity sessionEntity);

    Optional<StudentEntity> findById(Long id);

    int updateApprovalState(StudentEntity studentEntity);
}
