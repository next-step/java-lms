package nextstep.users.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.UsageType;
import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static nextstep.courses.domain.UsageType.FREE;
import static nextstep.courses.domain.UsageType.PAY;
import static nextstep.courses.domain.image.Type.GIF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("수강신청(register)을/를 하면 유저의 강의 목록에 추가가 된다")
    @Test
    void 수강신청() {
        Session tddCleanCode = new Session(1L, "tdd클린코드",10000, new Image(5, GIF, 300, 200), RECRUITING, FREE);
        JAVAJIGI.register(tddCleanCode);
        assertThat(JAVAJIGI.numberOfSession()).isEqualTo(1);
    }

    @DisplayName("강의의 상태가 모집중인지 검증한다")
    @Test
    void isRecruiting() {
        Session tddCleanCode = new Session(1L, "tdd클린코드",10000, new Image(5, GIF, 300, 200), RECRUITING, PAY);
        assertThat(tddCleanCode.isRecruiting()).isTrue();
    }

    @DisplayName("수강 신청을 할때 유료강의는 최대수강인원을 초과할 수 없다")
    @Test
    void validateUserLimitForPaidCourseInRegister() {
        assertThatThrownBy(() -> SANJIGI.register(new Session(List.of(JAVAJIGI), UsageType.PAY, RECRUITING, 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
