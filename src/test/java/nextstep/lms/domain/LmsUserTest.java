package nextstep.lms.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LmsUserTest {
    protected static final LmsUser admin1 = LmsUser.createAdmin("admin1", "password", "홍길동");
    protected static final LmsUser user1 = LmsUser.createUser("user1", "password", "김두한");

    @Test
    void 유저생성() {
        LmsUser user2 = LmsUser.createUser("user2", "password", "구마적");
        assertAll(
                () -> assertThat(user2.getId()).isEqualTo(3L),
                () -> assertThat(user2.getUserId()).isEqualTo("user2"),
                () -> assertThat(user2.getName()).isEqualTo("구마적"),
                () -> assertThat(user2.getRole()).isEqualTo(LmsUserRole.NORMAL),
                () -> assertThat(admin1.getRole()).isEqualTo(LmsUserRole.ADMIN),
                () -> assertThat(user1.getRole()).isEqualTo(LmsUserRole.NORMAL)
        );
    }
}
