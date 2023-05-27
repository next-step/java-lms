package nextstep.courses.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {

    @Test
    @DisplayName("유저들은 보유한 유저정보의 수를 알려준다.")
    void 유저들_정보수() {
        Users actual = Users.of(List.of(User.of(1L, Name.of(1L, "사용자"))));
        assertThat(actual.count()).isEqualTo(1L);
    }

    @Test
    @DisplayName("유저들은 입력된 유저를 추가한 뒤 해당 유저정보를 포함한다.")
    void 유저들_추가() {
        Users actual = Users.of(new ArrayList<>(List.of(User.of(1L, Name.of(1L, "사용자")))));
        User user = User.of(2L, Name.of(2L, "이름"));
        actual.add(user);

        assertThat(actual.isContains(user)).isTrue();
    }

    @Test
    @DisplayName("동일한 상태를 갖는 두 유저들 객체는 동일한 유저들이다.")
    void 유저들_생성() {
        Users actual = Users.of(List.of(User.of(1L, Name.of(1L, "사용자"))));
        assertThat(actual).isEqualTo(Users.of(List.of(User.of(1L, Name.of(1L, "사용자")))));
    }

}