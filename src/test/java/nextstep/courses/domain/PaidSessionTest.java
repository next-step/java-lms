package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PaidSessionTest {
    private PaidSession session;

    @BeforeEach
    public void sampleDataSetUp() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.jpg);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000+09:00", "2023-12-07T10:00:00.000+09:00");

        session = PaidSession.createNewSession(image, duration, 1, 100);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 최대 수강 인원에 도달했는데 또 사용자 추가하면 -> 예외 던짐")
    public void registerOverflowTest() {
        session.registerUser(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI);
        }).isInstanceOf(Exception.class);
    }
}