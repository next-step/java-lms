package nextstep.courses.domain.session;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.type.SessionStatus;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @DisplayName("무료강의 생성")
    @Test
    void 무료강의생성() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(true, 10000, new ParticipantCount(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isTrue();
    }

    @DisplayName("유료강의 생성")
    @Test
    void 유료강의생성() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isFalse();
    }

    @DisplayName("강의가 모집중일때 신청이 가능하다")
    @Test
    void 강의가_모집중일때_신청이_가능하다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY);
        // then
        assertThat(session.isRecruiting()).isTrue();
    }

    @DisplayName("강의가 모집중이 아닐때 신청이 불가능하다")
    @Test
    void 강의가_모집중이_아닐때_신청이_불가능하다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.FINISH);
        // then
        assertThatThrownBy(() -> session.addParticipant(10000, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유료강의 결제")
    @Test
    void 유료강의결제() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT);
        session.addParticipant(10000, NsUserTest.JAVAJIGI);
        // then
        assertThat(session.nowParticipantCount()).isEqualTo(1);
    }

    @DisplayName("유료강의 참여가 최대를 넘기면 예외가 발생한다.")
    @Test
    void 유료강의_참여가_최대를_넘기면_예외가_발생한다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT);
        session.addParticipant(10000, NsUserTest.JAVAJIGI);
        // then
        assertThatThrownBy(() -> session.addParticipant(10000, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유료강의 결제금액이 강의가격과 다르면 예외가 발생한다.")
    @Test
    void 유료강의_결제금액이_강의가격과_다르면_예외가_발생한다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantCount(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT);
        // then
        assertThatThrownBy(() -> session.addParticipant(5000, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
