package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.enroll.Enrolment;

import java.util.Optional;

public interface EnrolmentRepository {

    Optional<Enrolment> findById(Long id);
}
