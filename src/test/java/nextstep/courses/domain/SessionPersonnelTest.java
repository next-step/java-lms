package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

class SessionPersonnelTest {

    public static SessionPersonnel sessionPersonalMax = new SessionPersonnel(Integer.MAX_VALUE);

    @Test
    void 최대_인원_초과() {
        SessionPersonnel sessionPersonnel = new SessionPersonnel(1);

        sessionPersonnel.register(userA());
        assertThatThrownBy(() -> sessionPersonnel.register(userB())).isInstanceOf(
                RuntimeException.class)
            .hasMessage("최대 인원을 초과할 수 없습니다.");
    }

    @Test
    void 중복_유저_등록() {
        SessionPersonnel sessionPersonnel = new SessionPersonnel(2);
        sessionPersonnel.register(userA());
        assertThatThrownBy(() -> sessionPersonnel.register(userA())).isInstanceOf(
                RuntimeException.class)
            .hasMessage("중복 유저가 존재합니다.");
    }

    @Test
    void Guest_유저_등록() {
        SessionPersonnel sessionPersonnel = new SessionPersonnel(2);
        assertThatThrownBy(() -> sessionPersonnel.register(NsUser.GUEST_USER))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Guest는 신청을 할 수 없습니다.");
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }

    private static NsUser userB() {
        return new NsUser(2L, "B", "B", "B", "B@B.B");
    }

}
