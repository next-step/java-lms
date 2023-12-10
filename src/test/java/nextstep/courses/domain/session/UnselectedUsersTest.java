package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UnselectedUsersTest {
    @Test
    @DisplayName("of 메서드를 쓰면, List<NsUser>를 UnselectedUsers로 변환한다.")
    void of() {
        //given
        final List<NsUser> nsUsers = new ArrayList<>();

        //when
        final UnselectedUsers totalSelectStatusUsers = UnselectedUsers.of(nsUsers);

        //then
        assertThat(totalSelectStatusUsers).isNotNull();
    }

}
