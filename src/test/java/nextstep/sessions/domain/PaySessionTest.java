package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PaySessionTest {

    Session session;
    Payment payment;
    NsUser nsUser;

    @BeforeEach
    void setUp() {
        // 강의의 시작일, 종료일
        LocalDate startDate = LocalDate.of(2024, 04, 8);
        LocalDate endDate = LocalDate.of(2024, 04, 10);
        SessionPeriod sessionPeriod = new SessionPeriod(startDate, endDate);

        // 강의 커버 이미지 객체
        int imageByte = 1000; // kb 단위
        String imageType = "gif";
        int imageWidth = 300;
        int imageHeight = 200;
        SessionImage sessionImage = new SessionImage(imageByte, imageType, imageWidth, imageHeight);

        session = Session.createPaySession(0L, 0L, sessionPeriod, sessionImage, SessionStatus.PREPARING, 0, 1000);
        payment = new Payment(0L, 0L, 0L, 1000);
        nsUser = new NsUser(0L, "1", "1", "1", "1");
    }

    @Test
    @DisplayName("강의 상태가 모집 중이 아니라면 수강 신청이 불가능하다.")
    void paySessionTest() {
        assertThatThrownBy(() -> session.signUp(nsUser, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 모집 중이 아닙니다.");
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 신청 인원을 넘으면 수강 신청이 불가능하다.")
    void paySessionUserCountTest() {
        session.changeSessionStatusIsRecruiting();
        assertThatThrownBy(() -> session.signUp(nsUser, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의의 최대 수강인원을 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("유료 강의는 결제 정보가 다르면 수강 신청이 불가능하다.")
    void paySessionPaymentTest() {
        Session pay = Session.createPaySession(0L, 0L, new SessionPeriod(LocalDate.of(2024, 04, 8), LocalDate.of(2024, 04, 10)), new SessionImage(1000, "gif", 300, 200), SessionStatus.RECRUITING, 6, 2000);
        assertThatThrownBy(() -> pay.signUp(nsUser, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 정보가 다릅니다.");
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 신청 인원이 0보다 작을 수 없다.")
    void paySessionUserCountPositiveTest() {
        assertThatThrownBy(() -> Session.createPaySession(0L, 0L, new SessionPeriod(LocalDate.of(2024, 04, 8), LocalDate.of(2024, 04, 10)), new SessionImage(1000, "gif", 300, 200), SessionStatus.PREPARING, -1, 2000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0보다 작은 수가 올 수 없습니다.");
    }

    @Test
    @DisplayName("유료 강의는 수강료는 0보다 작을 수 없다.")
    void paySessionPricePositiveTest() {
        assertThatThrownBy(() -> Session.createPaySession(0L, 0L, new SessionPeriod(LocalDate.of(2024, 04, 8), LocalDate.of(2024, 04, 10)), new SessionImage(1000, "gif", 300, 200), SessionStatus.PREPARING, 1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0보다 작은 수가 올 수 없습니다.");
    }
}