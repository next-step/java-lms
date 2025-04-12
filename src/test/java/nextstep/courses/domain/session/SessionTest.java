package nextstep.courses.domain.session;

import nextstep.courses.strategy.FreePaymentStrategy;
import nextstep.courses.strategy.PaidPaymentStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private SessionImage image;
    private SessionDate date;
    private SessionState state;
    private Enrollment enrollment;

    @BeforeEach
    void init() {
        image = new SessionImage(300, 200, 10, "jpg");
        date = new SessionDate(LocalDate.now(), LocalDate.now().plusDays(7));
        state = SessionState.RECRUITING;
        enrollment = new Enrollment(10);
    }

    @Test
    @DisplayName("무료 강의를 등록한다.")
    void applyFreeSession() {
        FreePaymentStrategy freePaymentStrategy = new FreePaymentStrategy();
        Session session = new Session(image, date, state, enrollment, freePaymentStrategy);
        session.applySession(NsUserTest.JAVAJIGI, LocalDate.now().minusDays(1), new Payment());
    }

    @Test
    @DisplayName("유료 강의를 등록한다.")
    void applyPaidSession() {
        PaidPaymentStrategy paidPaymentStrategy = new PaidPaymentStrategy(1000L);
        Session session = new Session(image, date, state, enrollment, paidPaymentStrategy);
        session.applySession(NsUserTest.JAVAJIGI, LocalDate.now().minusDays(1), new Payment("1L", 1L, 1L, 1000L));
    }

    @Test
    @DisplayName("모집 상태가 아니면 등록할 수 없다.")
    void applySessionFailByState() {
        FreePaymentStrategy freePaymentStrategy = new FreePaymentStrategy();
        Session session = new Session(image, date, SessionState.PREPARING, enrollment, freePaymentStrategy);
        assertThatThrownBy(() -> session.applySession(NsUserTest.JAVAJIGI, LocalDate.now(), new Payment()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 시작 중에는 등록할 수 없다.")
    void applySessionFailByDate() {
        FreePaymentStrategy freePaymentStrategy = new FreePaymentStrategy();
        Session session = new Session(image, date, state, enrollment, freePaymentStrategy);
        assertThatThrownBy(() -> session.applySession(NsUserTest.JAVAJIGI, LocalDate.now().plusDays(10), new Payment()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈이 맞지 않으면 유료 강의에 등록할 수 없다.")
    void applySessionFailByPayment() {
        PaidPaymentStrategy paidPaymentStrategy = new PaidPaymentStrategy(1000L);
        Session session = new Session(image, date, state, enrollment, paidPaymentStrategy);
        assertThatThrownBy(() -> session.applySession(NsUserTest.JAVAJIGI, LocalDate.now(), new Payment("1L", 1L, 1L, 2000L)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}