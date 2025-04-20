package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SessionTest {

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void paidSessionEnrollTest() {
        //given
        CoverImage coverImage = new CoverImage(1024 * 1024, ImageType.GIF, 300, 200);
        Session session = Session.register(0L, coverImage, SessionType.PAID, 10000L, 100L);
        session.open();
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment("0", session.getId(), user.getId(), 10000L);
        LocalDateTime now = LocalDateTime.now();

        //when
        EnrolledStudent enroll = session.enroll(user, payment.getAmount(), now);

        //then
        Assertions.assertThat(enroll)
                .isNotNull()
                .extracting("userId", "session", "enrolledAt")
                .containsExactly(user.getUserId(), session, now);
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 다른 경우 에러를 던진다.")
    void paidSessionEnrollThrowTest() {
        //given
        CoverImage coverImage = new CoverImage(1024 * 1024, ImageType.GIF, 300, 200);
        Session session = Session.register(0L, coverImage, SessionType.PAID, 10000L, 100L);
        session.open();
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment("0", session.getId(), user.getId(), 100L);
        LocalDateTime now = LocalDateTime.now();

        //when&then
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(user, payment.getAmount(), now))
                .withMessageContaining("결제 금액이 수강료와 일치하지 않습니다.");

    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중이 아닌 경우 에러를 던진다.")
    void enrollClosedSessionThrowTest() {
        // given
        CoverImage coverImage = new CoverImage(1024 * 1024, ImageType.GIF, 300, 200);
        Session session = Session.register(0L, coverImage, SessionType.PAID, 10000L, 100L);
        session.close(); // <-- 도메인 메서드로 상태 변경

        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment("0", session.getId(), user.getId(), 10000L);
        LocalDateTime now = LocalDateTime.now();

        // when & then
        Assertions.assertThatThrownBy(() -> session.enroll(user, payment.getAmount(), now))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집 중이 아닙니다");
    }

    @Test
    @DisplayName("유료강의는 수강인원 제한이 있다")
    void sessionCapacityTest() {
        // given
        CoverImage coverImage = new CoverImage(1024 * 1024, ImageType.PNG, 300, 200);
        Session session = Session.register(0L, coverImage, SessionType.PAID, 10000L, 1L);
        session.open();
        NsUser user1 = NsUserTest.JAVAJIGI;
        NsUser user2 = NsUserTest.SANJIGI;

        LocalDateTime now = LocalDateTime.now();

        // when: 첫 번째 유저는 정상 수강
        session.enroll(user1, 10000L, now);

        // then: 두 번째 유저는 정원 초과로 예외 발생
        Assertions.assertThatThrownBy(() -> session.enroll(user2, 10000L, now))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("수강 정원을 초과했습니다");
    }

    @Test
    @DisplayName("무료강의는 수강인원 제한이 없다.")
    void freeSessionCapacityTest() {
        // given
        CoverImage coverImage = new CoverImage(1024 * 1024, ImageType.PNG, 300, 200);

        // when
        Session session = Session.register(0L, coverImage, SessionType.FREE, 0L, 999L);

        // then
        Assertions.assertThat(session)
                .isNotNull()
                .extracting("capacity")
                .isNull();
    }
}