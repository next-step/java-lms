package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionState.READY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    private Session session;

    @BeforeEach
    public void sampleDataSetUp() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.jpg);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000+09:00", "2023-12-07T10:00:00.000+09:00");

        session = Session.createNewSession(image, duration);
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
}