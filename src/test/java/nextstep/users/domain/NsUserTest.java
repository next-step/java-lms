package nextstep.users.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.image.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.Status.RECRUITING;
import static nextstep.courses.domain.UsageType.PAY;
import static nextstep.courses.domain.image.Type.GIF;
import static org.assertj.core.api.Assertions.*;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    void 수강신청() {
        Session tddCleanCode = new Session(1L, "tdd클린코드",10000, new Image(5, GIF, 300, 200), RECRUITING, PAY);
        JAVAJIGI.register(tddCleanCode);
        assertThat(JAVAJIGI.numberOfSession()).isEqualTo(1);
    }

    @DisplayName("강의의 상태가 모집중인지 검증한다")
    @Test
    void isRecruiting() {
        Session tddCleanCode = new Session(1L, "tdd클린코드",10000, new Image(5, GIF, 300, 200), RECRUITING, PAY);
        assertThat(tddCleanCode.isRecruiting()).isTrue();
    }
}
