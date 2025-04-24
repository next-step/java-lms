package nextstep.courses.domain.repository;

import nextstep.courses.domain.model.Applicant;

public interface ApplicantRepository {
    long save(Applicant course);

    Applicant findById(Long id);
}
