package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionPersonnelTest {

    @Test
    void 최대_인원_초과() {
        SessionPersonnel sessionPersonnel = new SessionPersonnel(1);

        sessionPersonnel.register(userA());
        Assertions.assertThatThrownBy(() -> sessionPersonnel.register(userB())).isInstanceOf(
                RuntimeException.class)
            .hasMessage("최대 인원을 초과할 수 없습니다.");
    }

    @Test
    void 중복_유저_등록() {
        SessionPersonnel sessionPersonnel = new SessionPersonnel(2);
        sessionPersonnel.register(userA());
        Assertions.assertThatThrownBy(() -> sessionPersonnel.register(userA())).isInstanceOf(
                RuntimeException.class)
            .hasMessage("중복 유저가 존재합니다.");
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }

    private static NsUser userB() {
        return new NsUser(2L, "B", "B", "B", "B@B.B");
    }

}
