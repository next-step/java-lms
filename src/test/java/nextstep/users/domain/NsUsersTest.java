package nextstep.users.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NsUsersTest {

    @DisplayName("size는 사용자 수를 반환한다.")
    @Test
    void size() {
        assertThat(new NsUsers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)).size())
                .isEqualTo(2);
    }

    @DisplayName("contains는 사용자가 포함되어 있는지 여부를 반환한다.")
    @Test
    void contains() {
        assertThat(new NsUsers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)).contains(NsUserTest.JAVAJIGI))
                .isTrue();
    }
}
