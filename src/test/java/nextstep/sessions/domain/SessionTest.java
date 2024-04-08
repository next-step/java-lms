package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

class SessionTest {

    private static final long SESSION_PRICE = 10000L;

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(2, Status.RECRUITING, SESSION_PRICE, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
    }

    @Test
    void 강의_최대_수강_인원을_초과한_경우_수강_신청에_실패한다() {
        session.enroll(NsUserTest.JAVAJIGI, SESSION_PRICE);
        session.enroll(NsUserTest.SANJIGI, SESSION_PRICE);

        assertThatIllegalArgumentException().isThrownBy(
                () -> session.enroll(new NsUser(3L, "lee", "password", "name", "lee@slipp.net"), SESSION_PRICE)
        ).withMessage("수강 인원이 초과되었습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"PREPARING", "END"})
    void 모집중이_아닌_강의인_경우_수강_신청을_하면_실패한다(final Status status) {
        final Session session = new Session(5, status, 100000L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));

        assertThatIllegalArgumentException().isThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, 100000L))
                .withMessage("모집중인 강의가 아닙니다.");
    }

    @Test
    void 시작일이_지난_강의를_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, Status.RECRUITING, 100000L, LocalDateTime.now().plusDays(1));

        assertThatIllegalArgumentException().isThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, 100000L))
                .withMessage("시작일이 지난 강의는 수강 신청할 수 없습니다.");
    }

    @Test
    void 결제_금액과_강의_금액이_일치하지_않으면_실패한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, 20000L))
                .withMessage("가격이 일치하지 않습니다.");
    }

    @Test
    void 무료_강의_수강_신청한다() {
        // given
        final Session freeSession = new Session(Status.RECRUITING);

        // when
        freeSession.enroll(NsUserTest.JAVAJIGI, 0L);

        // then
        assertThat(freeSession.getAttendees()).hasSize(1)
                .contains(NsUserTest.JAVAJIGI);
    }

    @Test
    void 유료_강의_수강_신청한다() {
        session.enroll(NsUserTest.JAVAJIGI, SESSION_PRICE);

        assertThat(session.getAttendees()).hasSize(1)
                .contains(NsUserTest.JAVAJIGI);
    }
}
