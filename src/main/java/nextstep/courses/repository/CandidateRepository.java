package nextstep.courses.repository;

import nextstep.courses.domain.session.Candidate;

import java.util.Set;

public interface CandidateRepository {
    Set<Candidate> findBySessionId(Long sessionId);
}
