package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.ImageName;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageSize;
import nextstep.courses.type.SessionStatus;
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
        Price price = new Price(true, 10000, new ParticipantManager(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY, null, null);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isTrue();
    }

    @DisplayName("유료강의 생성")
    @Test
    void 유료강의생성() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY, null, null);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isFalse();
    }

    @DisplayName("강의이미지 등록하여 강의 생성")
    @Test
    void 강의이미지_등록하여_강의_생성() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(10));
        Image image = new Image(new ImageName("image.png"), new ImageSize(1024 * 1024), new ImagePixel(300, 200), null);
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.READY, image, null);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isFalse();
    }

    @DisplayName("강의가 모집중일때 신청이 가능하다")
    @Test
    void 강의가_모집중일때_신청이_가능하다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT, null, null);
        session.addParticipant(10000, NsUserTest.SANJIGI);
        // then
        assertThat(session.nowParticipants()).isEqualTo(1);
    }

    @DisplayName("강의가 모집중이 아닐때 신청이 불가능하다")
    @Test
    void 강의가_모집중이_아닐때_신청이_불가능하다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.FINISH, null, null);
        // then
        assertThatThrownBy(() -> session.addParticipant(10000, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유료강의 결제")
    @Test
    void 유료강의결제() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT, null, null);
        session.addParticipant(10000, NsUserTest.JAVAJIGI);
        // then
        assertThat(session.nowParticipants()).isEqualTo(1);
    }

    @DisplayName("유료강의 참여가 최대를 넘기면 예외가 발생한다.")
    @Test
    void 유료강의_참여가_최대를_넘기면_예외가_발생한다() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(1));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT, null, null);
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
        Price price = new Price(false, 10000, new ParticipantManager(10));
        // when
        Session session = new Session("TDD", sessionPeriod, price, SessionStatus.RECRUIT, null, null);
        // then
        assertThatThrownBy(() -> session.addParticipant(5000, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
