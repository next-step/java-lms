package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {
    private PaidSession session;
    private NsUser user = NsUserTest.JAVAJIGI;


    @BeforeEach
    public void sampleDataSetUp() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.jpg);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000", "2023-12-07T10:00:00.000");
        session = new PaidSession(duration, image, 1, 100);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 최대 수강 인원에 도달했는데 또 사용자 추가하면 -> 예외 던짐")
    public void registerOverflowTest() {
        Payment javajigiPayment = new Payment("0", session, NsUserTest.JAVAJIGI, 100L);
        session.registerUser(NsUserTest.JAVAJIGI, javajigiPayment);

        Payment sanjigiPayment = new Payment("1", session, NsUserTest.SANJIGI, 100L);

        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI, sanjigiPayment);
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 강의료와 지불 금액이 같지 않은 상태로 수강 등록을 하면 -> 예외 던짐")
    public void amountNoMatchTest() {
        Payment wrongPayment = new Payment("0", session, NsUserTest.JAVAJIGI, 80L);

        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.JAVAJIGI, wrongPayment);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 지불한 사람과 등록하는 사람이 같지 않으면 -> 예외 던짐")
    public void userNoMatchTest() {
        Payment wrongPayment = new Payment("0", session, NsUserTest.JAVAJIGI, 100L);

        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI, wrongPayment);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}