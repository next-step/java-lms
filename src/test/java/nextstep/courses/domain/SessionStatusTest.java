package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("강의 상태 테스트")
class SessionStatusTest {

    @DisplayName("강의는 3가지의 상태를 가진다")
    @Test
    void test() {
        List<SessionStatus> sessionStatuses = Arrays.stream(SessionStatus.values()).collect(Collectors.toList());
        Assertions.assertThat(sessionStatuses.size()).isEqualTo(3);
        Assertions.assertThat(sessionStatuses)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(SessionStatus.PREPARING, SessionStatus.ENROLLING, SessionStatus.FINISHED);
    }

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsPreparing() {
        SessionStatus preparing = SessionStatus.PREPARING;
        boolean enrollmentPossible = preparing.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isFalse();
    }

    @DisplayName("강의 상태가 종료중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsFinished() {
        SessionStatus finished = SessionStatus.FINISHED;
        boolean enrollmentPossible = finished.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isFalse();
    }

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 있다")
    @Test
    void lectureStatusIsEnrolling() {
        SessionStatus enrolling = SessionStatus.ENROLLING;
        boolean enrollmentPossible = enrolling.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isTrue();
    }
}
