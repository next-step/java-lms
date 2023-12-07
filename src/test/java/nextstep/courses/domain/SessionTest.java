package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionState.*;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
    private Session session;

    @BeforeEach
    public void sampleDataSetUp() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000", "2023-12-07T10:00:00.000");

        session = new FreeSession(duration, image);
    }

    @Test
    @DisplayName("[Session.registerUser()] 같은 사용자가 이미 등록한 강의에 또 등록하면 -> 예외 던짐")
    public void duplicateRegisterTest() {
        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI);
            session.registerUser(NsUserTest.SANJIGI);
        })
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("[Session.registerUser()] 강의는 모집중 상태에서만 수강생 등록 가능")
    public void reigsterStateTest() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000", "2023-12-07T10:00:00.000");
        Session readySession = new FreeSession(duration, image);
        Session recruitSession = new FreeSession(duration, image);
        Session endSession = new FreeSession(duration, image);

        readySession.updateStateTo(READY);
        recruitSession.updateStateTo(RECRUIT);
        endSession.updateStateTo(END);

        assertThatCode(() -> {
            readySession.registerUser(NsUserTest.JAVAJIGI);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            recruitSession.registerUser(NsUserTest.JAVAJIGI);
        }).isInstanceOf(Exception.class);

        assertThatThrownBy(() -> {
            endSession.registerUser(NsUserTest.JAVAJIGI);
        }).isInstanceOf(Exception.class);
    }
}