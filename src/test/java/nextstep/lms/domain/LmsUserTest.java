package nextstep.lms.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LmsUserTest {
    protected static final LmsUser ADMIN_1 = LmsUser.createAdmin("admin1", "password", "홍길동");
    protected static final LmsUser ADMIN_2 = LmsUser.createAdmin("admin2", "password", "임꺽정");
    protected static final LmsUser USER_1 = LmsUser.createUser("user1", "password", "김두한");
    protected static final LmsUser USER_2 = LmsUser.createUser("user2", "password", "김이연");

    @Test
    void 유저생성() {
        LmsUser newUser = LmsUser.createUser("newUser", "password", "구마적");
        assertAll(
                () -> assertThat(newUser.isUserId("newUser")).isTrue(),
                () -> assertThat(newUser.isName("구마적")).isTrue(),
                () -> assertThat(newUser.isAdmin()).isFalse(),
                () -> assertThat(ADMIN_1.isAdmin()).isTrue(),
                () -> assertThat(USER_1.isAdmin()).isFalse()
        );
    }
}
