package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

class SessionTest {

    @Test
    void 강의_최대_수강_인원을_초과한_경우_수강_신청에_실패한다() {
        final Session session = new Session(2);

        session.addAttendee(NsUserTest.JAVAJIGI);
        session.addAttendee(NsUserTest.SANJIGI);

        assertThatIllegalArgumentException().isThrownBy(
                () -> session.addAttendee(new NsUser(3L, "lee", "password", "name", "lee@slipp.net"))
        ).withMessage("수강 인원이 초과되었습니다.");
    }
}
