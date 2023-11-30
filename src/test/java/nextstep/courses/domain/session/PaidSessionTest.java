package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {
    @Test
    @DisplayName("PaidSession 생성")
    void testPaidSessionConstructor() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final double price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when
        Session paidSession = new PaidSession(title, price, startDate, endDate);

        //then
        assertThat(paidSession).isNotNull();
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (강의가 처음 열리면 강의 상태는 READY이다.)")
    void testEnrollWithInitSession() {
        //given
        final Session paidSession = buildDefaultPaidSession();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (READY 상태)")
    void testEnrollWithSessionStatusIsReady() {
        //given
        final Session paidSession = buildDefaultPaidSession();
        paidSession.ready();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (CLOSED 상태)")
    void testEnrollWithSessionStatusIsClosed() {
        //given
        final Session paidSession = buildDefaultPaidSession();
        paidSession.close();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 지불한 가격이 강의의 가격과 맞지 않으면, 예외가 발생한다.")
    void testEnrollWithSessionPrice() {
        //given
        final double price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) (price - 1));

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("paid amount is different with price");
    }

    @Test
    @DisplayName("수강 신청시에 최대 수강 인원을 넘으면, 예외가 발생한다.")
    void testEnrollWithMaxStudentLimit() {
        //given
        final double price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) price);
        enrollForMaxLimit(paidSession, payment);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("max student limit is reached");
    }

    private void enrollForMaxLimit(final Session paidSession, final Payment payment) {
        for (int count = 0; count < 15; count++) {
            paidSession.enroll(payment);
        }
    }

    @Test
    @DisplayName("강의 상태가 모집중이고 강의 가격과 지불한 가격이 같으며 최대 수강 인원을 넘지 않으면, 수강 신청이 가능하다.")
    void testEnroll() {
        //given
        final double price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) price);

        final int currentStudentCountBeforeEnroll = paidSession.getCurrentStudentCount();

        //when
        paidSession.enroll(payment);
        final int currentStudentCountAfterEnroll = paidSession.getCurrentStudentCount();

        //then
        assertThat(currentStudentCountAfterEnroll).isEqualTo(currentStudentCountBeforeEnroll + 1);
    }

    private Session buildDefaultPaidSession() {
        final String title = "TDD, 클린 코드 with Java";
        final double price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        return new PaidSession(title, price, startDate, endDate);
    }

    private Session buildDefaultPaidSessionWithRecruitingStatusAndPrice(final double price) {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final PaidSession paidSession = new PaidSession(title, price, startDate, endDate);
        paidSession.recruit();

        return paidSession;
    }
}
