package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {

    private Long sessionId;
    private SessionPeriod sessionPeriod;
    private Payment zeroPayment;
    private Payment payment;
    private int maxCapacity;
    private SessionFee sessionFee;
    private CoverImage coverImage;
    String imageType = "JPG";
    int imageWidth = 300;
    int imageHeight = 200;
    int imageFileSize = 1024;

    @BeforeEach
    void setUp() {
        sessionId = 1L;
        sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024, 1, 1, 0, 0, 0),
                LocalDateTime.of(2024, 4, 1, 0, 0, 0));
        zeroPayment = new Payment("1", 123L, 1L, 0L);
        payment = new Payment("1", 123L, 1L, 800_000L);
        maxCapacity = 1;
        sessionFee = new SessionFee(new BigDecimal(800_000));
        coverImage = CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
    }

    @Test
    @DisplayName("PaidSession의 SessionType확인 : PAID")
    void sessionTypeCheckTest() {
        Session paidSession = new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage);
        assertThat(paidSession.sessionType()).isEqualTo(SessionType.PAID);
    }

    @Test
    @DisplayName("SessionStatus RECRUIT가 아니면 IllegalStateException")
    void sessionStatusCheckTest() {
        Session paidSession = new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage);

        assertThatThrownBy(() -> {
            paidSession.enroll(NsUserTest.JAVAJIGI, payment);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("강의 모집중이 아닙니다.");
    }

    @Test
    @DisplayName("최대수강인원 보다 현재 수강인원이 작거나 같으면 IllegalStateException")
    void sessionMaxCapacityTest() {
        Session paidSession = new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage);
        paidSession.start();
        paidSession.enroll(NsUserTest.SANJIGI, payment);

        assertThatThrownBy(() -> {
            paidSession.enroll(NsUserTest.JAVAJIGI, payment);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강인원을 초과 하였습니다.");
    }

    @Test
    @DisplayName("수강 신청 후 수강 인원 inscrese")
    void enrollInscreseTest() {
        Session paidSession = new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage);
        paidSession.start();
        paidSession.enroll(NsUserTest.JAVAJIGI, payment);
        assertThat(paidSession.countOfStudents()).isEqualTo(1);
    }

    @Test
    @DisplayName("Payment의 결제금액이 수강료와 일치 하지 않는 경우 IllegalStateException")
    void paymentTest() {
        Session paidSession = new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage);
        paidSession.start();

        assertThatThrownBy(() -> {
            paidSession.enroll(NsUserTest.JAVAJIGI, zeroPayment);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("결제금액이 수강료와 일치하지 않습니다.");

    }
}