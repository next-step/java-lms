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
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaidSessionTest {

    private PaidSession paidSession;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        SessionPeriod period = new SessionPeriod(startDate,
                endDate);
        CoverImage coverImage = CoverImage.of(ImageSize.of(500 * 1024), "jpg", ImageDimension.of(300, 200));
        paidSession = new PaidSession(1L, "이펙티브 자바", period, coverImage, 50000L, 2);
    }

    @Test
    @DisplayName("무료 강의 생성 시 필드가 올바르게 설정되는지 확인한다.")
    void createPaidSessionTest() {
        assertAll(
                () -> assertEquals("이펙티브 자바", paidSession.getTitle()),
                () -> assertEquals(startDate, paidSession.getPeriod().getStartDate()),
                () -> assertEquals(endDate, paidSession.getPeriod().getEndDate()),
                () -> assertEquals(300, paidSession.getCoverImage().getWidth()),
                () -> assertEquals(200, paidSession.getCoverImage().getHeight())
        );
    }

    @DisplayName("유료강의 모집중 상태이고, 수강 인원이 초과하지 않았고, 유효한 결제가 이루어지면 수강신청이 가능하다.")
    @Test
    void enrollUserSuccessfullyWhenStatusIsOpen() {
        paidSession.openEnrollment();

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));

        assertThat(paidSession.getEnrolledUsers()).contains(NsUserTest.JAVAJIGI);
    }

    @DisplayName("모집중인 상태가 아닌 유료강의를 수강신청하면 예외가 발생한다.")
    @Test
    void enrollTestThrowExceptionWhenStatusIsNotOpen() {
        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 상태에서만 신청 가능합니다.");
    }

    @DisplayName("결제금액이 일치하지 않으면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenPaidAmountDoesNotMatchFee() {
        paidSession.openEnrollment();

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 1L, 49000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @DisplayName("수강인원이 초과되면 예외가 발생한다.")
    @Test
    void failToEnrollUserWhenMaxEnrollmentsReached() {
        paidSession.openEnrollment();

        paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("1", 1L, 1L, 50000L));
        paidSession.enroll(NsUserTest.SANJIGI, new Payment("1", 1L, 2L, 50000L));

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.POBIJIGI, new Payment("1", 1L, 3L, 50000L)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("수강 인원이 초과되었습니다.");
    }

}
