package nextstep.courses.infrastructure;

import nextstep.courses.entity.StudentEntity;

public interface StudentRepository {

    int save(StudentEntity sessionEntity);
}
