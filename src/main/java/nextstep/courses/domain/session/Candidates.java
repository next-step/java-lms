package nextstep.courses.domain.session;

import java.util.HashSet;
import java.util.Set;

public class Candidates {
    private final Set<Candidate> candidates = new HashSet<>();

    public void initCandidates(Set<Candidate> candidates) {
        this.candidates.addAll(candidates);
    }

    public void validateApprove(Long sessionId, Long userId) {
        if (!candidates.contains(new Candidate(sessionId, userId))) {
            throw new IllegalArgumentException("해당 강의에 선발된 사용자가 아닙니다.");
        }
    }

    public void validateReject(Long sessionId, Long userId) {
        if (candidates.contains(new Candidate(sessionId, userId))) {
            throw new IllegalArgumentException("해당 강의에 선발된 사용자는 임의로 취소할 수 없습니다.");
        }
    }
}
