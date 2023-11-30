package nextstep.courses.domain;

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
        PaidSession paidSession = new PaidSession(title, price, startDate, endDate);

        //then
        assertThat(paidSession.getTitle()).isEqualTo(title);
        assertThat(paidSession.getPrice()).isEqualTo(price);
        assertThat(paidSession.getStartDate()).isEqualTo(startDate);
        assertThat(paidSession.getEndDate()).isEqualTo(endDate);
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (강의가 처음 열리면 강의 상태는 READY이다.)")
    void testEnrollWithInitSession() {
        //given
        final PaidSession paidSession = buildDefaultPaidSession();
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
        final PaidSession paidSession = buildDefaultPaidSession();
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
        final PaidSession paidSession = buildDefaultPaidSession();
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

        final PaidSession paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) (price - 1));

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("paid amount is different with price");
    }

    private PaidSession buildDefaultPaidSession() {
        final String title = "TDD, 클린 코드 with Java";
        final double price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        return new PaidSession(title, price, startDate, endDate);
    }

    private PaidSession buildDefaultPaidSessionWithRecruitingStatusAndPrice(final double price) {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final PaidSession paidSession = new PaidSession(title, price, startDate, endDate);
        paidSession.recruit();

        return paidSession;
    }
}
