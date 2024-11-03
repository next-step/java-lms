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
        coverImage = CoverImage.of(ImageSize.of(500 * 1024), "jpg", ImageDimension.of(300, 200));
        sessionBody = SessionBody.of("이펙티브 자바", period, coverImage);
    }

    @Test
    @DisplayName("무료 강의 생성 시 필드가 올바르게 설정되는지 확인한다.")
    void createPaidSessionTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, sessionBody, sessionEnrollment, 50000L, 2);
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
        PaidSession paidSession = new PaidSession(1L, sessionBody, sessionEnrollment, 50000L, 2);

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));

        assertThat(paidSession.getEnrolledUsers()).contains(NsUserTest.JAVAJIGI);
    }

    @DisplayName("모집중인 상태가 아닌 유료강의를 수강신청하면 예외가 발생한다.")
    @Test
    void enrollTestThrowExceptionWhenStatusIsNotOpen() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.PREPARE);
        PaidSession paidSession = new PaidSession(1L, sessionBody, sessionEnrollment, 50000L, 2);

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 상태에서만 신청 가능합니다.");
    }

    @DisplayName("결제금액이 일치하지 않으면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenPaidAmountDoesNotMatchFee() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, sessionBody, sessionEnrollment, 50000L, 2);

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 49000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @DisplayName("수강인원이 초과되면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenMaxEnrollmentsReached() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        PaidSession paidSession = new PaidSession(1L, sessionBody, sessionEnrollment, 50000L, 2);

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));
        paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 2L, 50000L));

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.POBIJIGI, new Payment("1", 1L, 3L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("수강 인원이 초과되었습니다.");
    }

}
