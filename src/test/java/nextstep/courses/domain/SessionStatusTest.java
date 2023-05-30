package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsPreparing() {
        SessionStatus preparing = SessionStatus.PREPARING;
        assertThatIllegalArgumentException()
                .isThrownBy(preparing::isEnrollmentPossible)
                .withMessage("the current session is not in the enrolling status");
    }

    @DisplayName("강의 상태가 종료중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsFinished() {
        SessionStatus finished = SessionStatus.FINISHED;
        assertThatIllegalArgumentException()
                .isThrownBy(finished::isEnrollmentPossible)
                .withMessage("the current session is not in the enrolling status");
    }

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 있다")
    @Test
    void lectureStatusIsEnrolling() {
        SessionStatus enrolling = SessionStatus.ENROLLING;
        boolean enrollmentPossible = enrolling.isEnrollmentPossible();
        assertThat(enrollmentPossible).isTrue();
    }
}
