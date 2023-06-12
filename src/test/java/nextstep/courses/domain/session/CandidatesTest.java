package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class CandidatesTest {
    Candidates candidates = new Candidates();

    @BeforeEach
    void setUp() {
        this.candidates = new Candidates();
    }

    @DisplayName("선발되지 않은 사용자는 수강신청 승인할 수 없다.")
    @Test
    void validateApproveTest() {
        candidates.initCandidates(Set.of(new Candidate(0L, 0L)));
        Assertions.assertThatThrownBy(() -> candidates.validateApprove(0L, 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("선발된 사용자는 수강 신청을 임의로 취소시킬 수 없다.")
    @Test
    void validateRejectTest() {
        candidates.initCandidates(Set.of(new Candidate(0L, 0L)));
        Assertions.assertThatThrownBy(() -> candidates.validateReject(0L, 0L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
