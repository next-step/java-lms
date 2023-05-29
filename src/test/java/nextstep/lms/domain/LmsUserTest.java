package nextstep.lms.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LmsUserTest {
    protected static final LmsUser ADMIN_1 = LmsUser.adminOf("admin1", "password", "홍길동");
    protected static final LmsUser ADMIN_2 = LmsUser.adminOf("admin2", "password", "임꺽정");
    protected static final LmsUser USER_1 = LmsUser.normalOf("user1", "password", "김두한");
    protected static final LmsUser USER_2 = LmsUser.normalOf("user2", "password", "김이연");

    @Test
    void 유저생성() {
        LmsUser newUser = LmsUser.normalOf("newUser", "password", "구마적");
        assertAll(
                () -> assertThat(newUser.isUserId("newUser")).isTrue(),
                () -> assertThat(newUser.isName("구마적")).isTrue(),
                () -> assertThat(newUser.isAdmin()).isFalse(),
                () -> assertThat(ADMIN_1.isAdmin()).isTrue(),
                () -> assertThat(USER_1.isAdmin()).isFalse()
        );
    }

    @Test
    void 유저_필수값_미입력시() {
        assertAll(
                () -> assertThatThrownBy(() -> LmsUser.adminOf(null, "password", "구마적")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("newUser", null, "구마적")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("newUser", "password", null)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("", "password", "구마적")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("   ", "password", "구마적")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("newUser", "", "구마적")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> LmsUser.adminOf("newUser", "password", "")).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
