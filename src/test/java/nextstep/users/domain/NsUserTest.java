package nextstep.users.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.PAID;
import static nextstep.sessions.domain.image.ImageType.GIF;
import static org.assertj.core.api.Assertions.assertThat;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("강의의 상태가 모집중인지 검증한다")
    @Test
    void isRecruiting() {
        Session tddCleanCode = new Session(1L, "tdd클린코드", new Image(5, GIF, 300, 200), RECRUITING, PAID);
        assertThat(tddCleanCode.isRecruiting()).isTrue();
    }

}
