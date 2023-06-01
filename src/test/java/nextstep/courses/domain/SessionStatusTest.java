package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("강의 상태 테스트")
class SessionStatusTest {

    @DisplayName("강의는 3가지의 상태를 가진다")
    @Test
    void test() {
        List<SessionStatus> sessionStatuses = Arrays.stream(SessionStatus.values()).collect(Collectors.toList());
        assertThat(sessionStatuses.size()).isEqualTo(3);
        assertThat(sessionStatuses)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(SessionStatus.PREPARING, SessionStatus.ENROLLING, SessionStatus.FINISHED);
    }

    @DisplayName("강의 상태가 준비증인지 확인 할 수 있다")
    @Test
    void lectureStatusIsPreparing() {
        SessionStatus preparing = SessionStatus.PREPARING;
        assertThat(preparing.isPreparing()).isTrue();
    }

    @DisplayName("강의 상태가 모집중인지 확인 할 수 있다")
    @Test
    void lectureStatusIsFinished() {
        SessionStatus enrolling = SessionStatus.ENROLLING;
        assertThat(enrolling.isEnrolling()).isTrue();
    }

    @DisplayName("강의 상태가 종료인지 확인 할 수 있다")
    @Test
    void lectureStatusIsEnrolling() {
        SessionStatus finished = SessionStatus.FINISHED;
        assertThat(finished.isFinished()).isTrue();
    }
}
