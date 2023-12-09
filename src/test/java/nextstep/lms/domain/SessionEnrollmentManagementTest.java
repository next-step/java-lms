package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionStatusEnum;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionEnrollmentManagementTest {
    @DisplayName("강의 상태가 모집중이 아닐경우 수강생 등록 시 예외발생")
    @Test
    void 강의_상태_확인() {
        SessionEnrollmentManagement completedSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.FREE, 0L),
                SessionStatusEnum.COMPLETED, Integer.MAX_VALUE);
        final Students students = new Students(Collections.emptyList());
        final Payment payment = new Payment("1", 1L, 1L, 0L);

        assertThatThrownBy(() -> completedSession.enroll(students, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모집중이 아닙니다.");
    }

    @DisplayName("모집중인 무료 강의 수강생 등록 성공")
    @Test
    void 무료강의_수강생_등록() {
        SessionEnrollmentManagement recruitingSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.FREE, 0L),
                SessionStatusEnum.RECRUITING, Integer.MAX_VALUE);
        final Students students = new Students(Collections.emptyList());
        final Payment payment = new Payment("1", 1L, 1L, 0L);
        recruitingSession.enroll(students, payment);
        assertThat(students.size()).isEqualTo(1);
    }

    @DisplayName("모집중인 유료 강의 수강생 등록 성공")
    @Test
    void 유료강의_수강생_등록() {
        SessionEnrollmentManagement recruitingSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.PAID, 800_000L),
                SessionStatusEnum.RECRUITING, Integer.MAX_VALUE);
        final Students students = new Students(Collections.emptyList());
        final Payment payment = new Payment("1", 1L, 1L, 800_000L);
        recruitingSession.enroll(students, payment);
        assertThat(students.size()).isEqualTo(1);
    }

    @DisplayName("모집중인 유료 강의 수강생 초과시 예외발생")
    @Test
    void 유료강의_수강생_등록_실패() {
        SessionEnrollmentManagement recruitingSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.PAID, 800_000L),
                SessionStatusEnum.RECRUITING, 1);
        final Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        final Payment payment = new Payment("1", 1L, 2L, 800_000L);

        assertThatThrownBy(() -> recruitingSession.enroll(students, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
    }
}