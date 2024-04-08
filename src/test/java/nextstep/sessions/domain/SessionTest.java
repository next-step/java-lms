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

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(2, Status.RECRUITING, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
    }

    @Test
    void 강의_최대_수강_인원을_초과한_경우_수강_신청에_실패한다() {
        session.addAttendee(NsUserTest.JAVAJIGI);
        session.addAttendee(NsUserTest.SANJIGI);

        assertThatIllegalArgumentException().isThrownBy(
                () -> session.addAttendee(new NsUser(3L, "lee", "password", "name", "lee@slipp.net"))
        ).withMessage("수강 인원이 초과되었습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"PREPARING", "END"})
    void 모집중이_아닌_강의인_경우_수강_신청을_하면_실패한다(final Status status) {
        final Session session = new Session(5, status, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));

        assertThatIllegalArgumentException().isThrownBy(() -> session.addAttendee(NsUserTest.JAVAJIGI))
                .withMessage("모집중인 강의가 아닙니다.");
    }

    @Test
    void 모집중인_강의인_경우_수강_신청을_하면_성공한다() {
        session.addAttendee(NsUserTest.JAVAJIGI);

        assertThat(session.getAttendees()).hasSize(1)
                .contains(NsUserTest.JAVAJIGI);
    }

    @Test
    void 시작일이_지난_강의를_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, Status.RECRUITING, LocalDateTime.now().plusDays(1));

        assertThatIllegalArgumentException().isThrownBy(() -> session.addAttendee(NsUserTest.JAVAJIGI));
    }
}
