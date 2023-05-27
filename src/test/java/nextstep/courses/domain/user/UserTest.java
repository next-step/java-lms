package nextstep.courses.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    public static final User TEST_USER = User.of(1L, Name.of(1L, "사용자"));

    @Test
    @DisplayName("동일한 상태의 두 사용자는 동일한 사용자이다.")
    void 사용자_생성() {
        assertThat(User.of(1L, Name.of(1L, "사용자"))).isEqualTo(User.of(1L, Name.of(1L, "사용자")));
    }

}