package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
    private NsUser student = NsUserTest.JAVAJIGI;

    private LocalDate startDate = LocalDate.of(2024, 4, 1);
    private LocalDate endDate = LocalDate.of(2024, 5, 1);

    @Test
    @DisplayName("무료 강의 수강신청 되는 지 테스트")
    void testEnrollment() {
        FreeSession freeSession = new FreeSession(SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
        Payment payment = freeSession.enrollmentUser(student);

        assertThat(payment.getSessionId()).isEqualTo(freeSession.getId());
        assertThat(payment.getAmount()).isEqualTo(0);
        assertThat(payment.getNsUserId()).isEqualTo(student.getId());
        assertThat(freeSession.getStudents()).hasSize(1).containsExactly(student);
    }

    @Test
    @DisplayName("수강신청한 사람이 한 번 더 수강신청할 경우 에러 발생")
    void testDuplicateEnrollment() {
        FreeSession freeSession = new FreeSession(SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
        freeSession.enrollmentUser(student);

        assertThatThrownBy(() -> freeSession.enrollmentUser(student)).isInstanceOf(NotRecruitException.class);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUIT"})
    @DisplayName("모집 중이 아닌 강의에 수강신청 하는 경우 에러 발생")
    void testInvalidEnrollmentUser(SessionStatus sessionStatus) {
        FreeSession freeSession = new FreeSession(SessionImageTest.S1, sessionStatus, SessionDateTest.of());

        assertThatThrownBy(() -> freeSession.enrollmentUser(student)).isInstanceOf(NotRecruitException.class);
    }
}
