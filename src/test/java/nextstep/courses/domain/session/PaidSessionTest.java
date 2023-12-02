package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
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
        final long price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when
        Session paidSession = new PaidSession(title, price, startDate, endDate);

        //then
        assertThat(paidSession).isNotNull();
    }

    @Test
    @DisplayName("Session의 수강료를 지정할 수 있다.")
    void testSessionConstructorHasPrice() {
        //given
        final int price = 1500;
        final Session session = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);

        //when
        final long getPrice = session.getPrice();

        //then
        assertThat(getPrice).isEqualTo(price);
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (READY 상태)")
    void testEnrollWithSessionStatusIsReady() {
        //given
        final Session paidSession = buildDefaultPaidSession();
        paidSession.ready();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment, NsUserTest.JAVAJIGI))
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
        assertThatThrownBy(() -> paidSession.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 지불한 가격이 강의의 가격과 맞지 않으면, 예외가 발생한다.")
    void testEnrollWithSessionPrice() {
        //given
        final long price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) (price - 1));

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("paid amount is different with price");
    }

    @Test
    @DisplayName("수강 신청시에 최대 수강 인원을 넘으면, 예외가 발생한다.")
    void testEnrollWithMaxStudentLimit() {
        //given
        final long price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) price);
        enrollForMaxLimit(paidSession, payment);

        //when, then
        assertThatThrownBy(() -> paidSession.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("max student limit is reached");
    }

    private void enrollForMaxLimit(final Session paidSession, final Payment payment) {
        for (int count = 0; count < 15; count++) {
            paidSession.enroll(payment, buildTempUser(count));
        }
    }

    private NsUser buildTempUser(final int count) {
        return new NsUser((long) (count + 100), "tempId" + count, "password", "name" + count, "temp" + count + "@slipp.net");
    }

    @Test
    @DisplayName("강의 상태가 모집중이고 강의 가격과 지불한 가격이 같으며 최대 수강 인원을 넘지 않으면, 수강 신청이 가능하다.")
    void testEnroll() {
        //given
        final long price = 3000;

        final Session paidSession = buildDefaultPaidSessionWithRecruitingStatusAndPrice(price);
        Payment payment = new Payment("tddJava", 0L, 0L, (long) price);

        final int currentStudentCountBeforeEnroll = paidSession.getCurrentStudentCount();

        //when
        paidSession.enroll(payment, NsUserTest.JAVAJIGI);
        final int currentStudentCountAfterEnroll = paidSession.getCurrentStudentCount();

        //then
        assertThat(currentStudentCountAfterEnroll).isEqualTo(currentStudentCountBeforeEnroll + 1);
    }

    private Session buildDefaultPaidSession() {
        final String title = "TDD, 클린 코드 with Java";
        final long price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        return new PaidSession(title, price, startDate, endDate);
    }

    private Session buildDefaultPaidSessionWithRecruitingStatusAndPrice(final long price) {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final PaidSession paidSession = new PaidSession(title, price, startDate, endDate);
        paidSession.recruit();

        return paidSession;
    }
}
