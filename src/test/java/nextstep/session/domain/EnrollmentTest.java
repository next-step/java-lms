package nextstep.session.domain;

import static nextstep.session.domain.Enrollment.SESSION_FULL_OF_PARTICIPANTS_EXCEPTION;
import static nextstep.session.domain.PaymentType.COMPLETED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.session.exception.SessionFullOfParticipantsException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    @Test
    @DisplayName("유료 강의의 경우, 수강 인원이 모두 채워져 강의를 신청할 수 없으면 예외를 던진다.")
    void full_session() {
        // given
        Enrollment enrollment = new Enrollment(new SessionParticipants(List.of(new NsUser()), 1), null, 10000);

        // when // then
        assertThatThrownBy(() -> enrollment.enrollPaidSession(10000, new NsUser()))
                .isExactlyInstanceOf(SessionFullOfParticipantsException.class)
                .hasMessage(SESSION_FULL_OF_PARTICIPANTS_EXCEPTION);
    }

    @Test
    @DisplayName("유료 강의를 등록하다.")
    void enroll_paid_session() {
        // given
        Enrollment enrollment = new Enrollment(new SessionParticipants(List.of(new NsUser()), 4),
                List.of(new SessionPayment(new NsUser(), COMPLETED)), 10000);

        // when
        NsUser participant = new NsUser(1L, "wlscww", "1234", "이수찬", "email");
        enrollment.enrollPaidSession(10000, participant);

        // then
        assertThat(enrollment.getParticipants().getParticipants()).contains(participant);
        assertThat(enrollment.getPayments()).contains(new SessionPayment(participant, COMPLETED));
    }

    @Test
    @DisplayName("무료 걍의를 등록한다.")
    void enroll_free_session() {
        // given
        Enrollment enrollment = new Enrollment(new SessionParticipants(List.of(new NsUser()), 4),
                List.of(new SessionPayment(new NsUser(), COMPLETED)), 0);

        // when
        NsUser participant = new NsUser(1L, "wlscww", "1234", "이수찬", "email");
        enrollment.enrollFreeSession(participant);

        // then
        assertThat(enrollment.getParticipants().getParticipants()).contains(participant);
        assertThat(enrollment.getPayments()).contains(new SessionPayment(participant, COMPLETED));
    }
}
