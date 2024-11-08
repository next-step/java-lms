package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PaidSessionTest {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionPeriod period;
    private CoverImage coverImage;
    private SessionBody sessionBody;

    @BeforeEach
    void setUp() {
        startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        period = SessionPeriod.of(startDate, endDate);
        coverImage = CoverImage.of("effective java", ImageSize.of(500 * 1024), "jpg", ImageDimension.of(300, 200));
        sessionBody = SessionBody.of("이펙티브 자바", period, coverImage);
    }

    @Test
    @DisplayName("유료 강의 생성 시 필드가 올바르게 설정되는지 확인한다.")
    void createPaidSessionTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, 1L, sessionBody, sessionEnrollment, 50000L, 2);
        assertAll(
                () -> assertThat("이펙티브 자바").isEqualTo(paidSession.getTitle()),
                () -> assertThat(startDate).isEqualTo(paidSession.getPeriod().getStartDate()),
                () -> assertThat(endDate).isEqualTo(paidSession.getPeriod().getEndDate()),
                () -> assertThat(300).isEqualTo(paidSession.getCoverImage().getWidth()),
                () -> assertThat(200).isEqualTo(paidSession.getCoverImage().getHeight())
        );
    }

    @DisplayName("유료강의 모집중 상태이고, 수강 인원이 초과하지 않았고, 유효한 결제가 이루어지면 수강신청이 가능하다.")
    @Test
    void enrollUserSuccessfullyWhenStatusIsOpen() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, 1L, sessionBody, sessionEnrollment, 50000L, 2);

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));

        assertThat(paidSession.getEnrolledUsers()).contains(NsUserTest.JAVAJIGI);
    }

    @DisplayName("모집중인 상태가 아닌 유료강의를 수강신청하면 예외가 발생한다.")
    @Test
    void enrollTestThrowExceptionWhenStatusIsNotOpen() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.PREPARE);
        PaidSession paidSession = new PaidSession(1L, 1L, sessionBody, sessionEnrollment, 50000L, 2);

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 상태에서만 신청 가능합니다.");
    }

    @DisplayName("결제금액이 일치하지 않으면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenPaidAmountDoesNotMatchFee() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, 1L, sessionBody, sessionEnrollment, 50000L, 2);

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 49000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @DisplayName("수강인원이 초과되면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenMaxEnrollmentsReached() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, 1L, sessionBody, sessionEnrollment, 50000L, 2);

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));
        paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 2L, 50000L));

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.POBIJIGI, new Payment("1", 1L, 3L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("수강 인원이 초과되었습니다.");
    }

    @Test
    @DisplayName("수강료(fee)가 0이거나 음수일 때 예외 발생")
    void validateFeeTest() {
        long invalidFee = 0;
        int validMaxEnrollments = 10;
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);

        assertThatThrownBy(
                () -> new PaidSession(1L, 1L, sessionBody, sessionEnrollment, invalidFee, validMaxEnrollments)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 수강료가 0원을 초과하여야 합니다.");
    }

    @Test
    @DisplayName("최대 수강 인원이 0이거나 음수일 때 예외 발생한다.")
    void validateMaxEnrollmentsTest() {
        long validFee = 10000;
        int invalidMaxEnrollments = 0;
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);

        assertThatThrownBy(
                () -> new PaidSession(1L, 1L, sessionBody, sessionEnrollment, validFee, invalidMaxEnrollments)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 최대 수강 인원 1명 이상이어야 합니다.");
    }

}
