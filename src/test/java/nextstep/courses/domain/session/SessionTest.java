package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private final String defaultTitle = "TDD, 클린 코드 with Java";
    private final LocalDateTime defaultStartDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    private final LocalDateTime defaultEndDate = LocalDateTime.of(2024, 12, 31, 0, 0);

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 status는 Ready 상태를 가진다.")
    void testInitSessionStatusIsReady() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final SessionStatus status = session.getStatus();

        //then
        assertThat(status).isEqualTo(SessionStatus.READY);
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 최대 수강인원을 15명으로 지정한다. (따로 요구한 사항이 없으므로, 임의로 지정)")
    void testInitSessionMaxStudentLimitIs15() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final int maxStudentLimit = session.getMaxStudentLimit();

        //then
        assertThat(maxStudentLimit).isEqualTo(15);
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 현재 수강인원을 0명으로 지정한다.")
    void testInitSessionCurrentStudentCountIs0() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final int currentStudentCount = session.getCurrentStudentCount();

        //then
        assertThat(currentStudentCount).isEqualTo(0);
    }


    @Test
    @DisplayName("Session의 수강료를 지정할 수 있다.")
    void testSessionConstructorHasPrice() {
        //given
        final int price = 1500;
        final Session session = new PaidSession(defaultTitle, price, defaultStartDate, defaultEndDate);

        //when
        final double getPrice = session.getPrice();

        //then
        assertThat(getPrice).isEqualTo(price);
    }

    @Test
    @DisplayName("Session의 수강료를 0원 미만으로 지정할 시, 예외가 발생한다.")
    void testSessionPriceIsBiggerThanAndEqualTo0() {
        //given
        final int price = -1;

        //when, then
        assertThatThrownBy(() -> new PaidSession(defaultTitle, price, defaultStartDate, defaultEndDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("price cannot be negative");
    }
}
