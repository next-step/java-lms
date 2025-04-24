package nextstep.courses.domain.repository;

import nextstep.courses.domain.model.Applicant;

public interface StudentRepository {
    long save(Applicant course);

    Applicant findById(Long id);
}
