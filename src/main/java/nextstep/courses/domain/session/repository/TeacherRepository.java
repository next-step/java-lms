package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.Teacher;

import java.util.Optional;

public interface TeacherRepository {

    Optional<Teacher> findById(Long id);
}
