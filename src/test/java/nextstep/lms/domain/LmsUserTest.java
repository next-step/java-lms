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
                () -> assertThat(newUser.getUserId()).isEqualTo("newUser"),
                () -> assertThat(newUser.getName()).isEqualTo("구마적"),
                () -> assertThat(newUser.getRole()).isEqualTo(LmsUserRole.NORMAL),
                () -> assertThat(ADMIN_1.getRole()).isEqualTo(LmsUserRole.ADMIN),
                () -> assertThat(USER_1.getRole()).isEqualTo(LmsUserRole.NORMAL)
        );
    }
}
