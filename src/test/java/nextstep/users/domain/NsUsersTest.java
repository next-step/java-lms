package nextstep.users.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NsUsersTest {


    @DisplayName("유저 인원수를 조회할 수 있다.")
    @Test
    void isSmallerThanMaxSize() {
        NsUsers nsUsers = NsUsers.from(List.of(new NsUser(), new NsUser(), new NsUser()));
        Assertions.assertThat(nsUsers.isSmallerThanMaxSize(4)).isTrue();
    }
}
