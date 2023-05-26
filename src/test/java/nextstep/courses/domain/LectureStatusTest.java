package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("강의 상태 테스트")
class LectureStatusTest {

    @DisplayName("강의는 3가지의 상태를 가진다")
    @Test
    void test() {
        List<LectureStatus> lectureStatuses = Arrays.stream(LectureStatus.values()).collect(Collectors.toList());
        Assertions.assertThat(lectureStatuses.size()).isEqualTo(3);
        Assertions.assertThat(lectureStatuses)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(LectureStatus.PREPARING, LectureStatus.ENROLLING, LectureStatus.FINISHED);
    }

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsPreparing() {
        LectureStatus preparing = LectureStatus.PREPARING;
        boolean enrollmentPossible = preparing.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isFalse();
    }

    @DisplayName("강의 상태가 종료중이면 수강 신청 할 수 없다")
    @Test
    void lectureStatusIsFinished() {
        LectureStatus finished = LectureStatus.FINISHED;
        boolean enrollmentPossible = finished.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isFalse();
    }

    @DisplayName("강의 상태가 준비중이면 수강 신청 할 수 있다")
    @Test
    void lectureStatusIsEnrolling() {
        LectureStatus enrolling = LectureStatus.ENROLLING;
        boolean enrollmentPossible = enrolling.isEnrollmentPossible();
        Assertions.assertThat(enrollmentPossible).isTrue();
    }
}
