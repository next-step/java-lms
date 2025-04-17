package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EnrollmentCapacityTest {
    @Test
    @DisplayName("정원이 가득 찬 경우 인원 증가 시 예외를 던진다.")
    void 등록_인원_초과() {
        EnrollmentCapacity enrollmentCapacity = new EnrollmentCapacity(1, 1);
        assertThatThrownBy(enrollmentCapacity::increaseEnrollment)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("정원이 초과되었습니다.");
    }

    @Test
    @DisplayName("정원이 가득 차지 않은 경우 등록 인원을 정상적으로 증가시킨다..")
    void 등록_인원_증가() {
        EnrollmentCapacity enrollmentCapacity = new EnrollmentCapacity(1, 0);
        enrollmentCapacity.increaseEnrollment();
    }
}