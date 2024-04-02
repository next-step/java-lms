package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static nextstep.sessions.domain.SessionState.FINISHED;
import static nextstep.sessions.domain.SessionState.PREPARING;
import static nextstep.sessions.domain.SessionState.RECRUITING;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    void create() {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session("TDD, 자바 18기", PREPARING, now);
        assertThat(session).isEqualTo(new Session("TDD, 자바 18기", PREPARING, now));
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUITING"})
    void 강의상태가_모집중이_아닌경우_신청불가하다(SessionState sessionState) {
        Session session = new Session("TDD, 자바 18기", sessionState);
        assertThatThrownBy(() -> session.requestJoin(JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모집종료된_무료강의는_신청불가하다() {
        Session session = new Session("TDD, 자바 18기", FINISHED, 0, new FreeSession());
        assertThatThrownBy(() -> session.requestJoin(JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모집중인_유료강의가_수강인원을_초과한_경우_신청할수없다() {
        Session session = new Session("TDD, 자바 18기", RECRUITING, 10, new PaidSession(10, Money.wons(1000L)));
        assertThatThrownBy(() -> session.requestJoin(JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수강신청가능한경우_주문서를생성한다() {
        Session session = new Session("TDD, 자바 18기", RECRUITING);

        LocalDateTime now = LocalDateTime.now();
        Payment payment = session.requestJoin(JAVAJIGI, now);

        assertThat(payment).isEqualTo(new Payment(0L, 1L, Money.ZERO, now));
    }


}
