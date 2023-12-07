package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UndecidedUsersTest {
    @Test
    @DisplayName("of 메서드를 쓰면, List<NsUser>를 UndecidedUsers로 변환한다.")
    void of() {
        //given
        final List<NsUser> nsUsers = new ArrayList<>();

        //when
        final UndecidedUsers totalSelectStatusUsers = UndecidedUsers.of(nsUsers);

        //then
        assertThat(totalSelectStatusUsers).isNotNull();
    }
}
