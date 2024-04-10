package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.fixture.NsUserFixture.nsUser;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionCapacityTest {

    @Test
    @DisplayName("[성공] 수강 인원을 생성한다.")
    void 수강_인원생성() {
        assertThatNoException()
                .isThrownBy(() -> sessionCapacity(MAX_CAPACITY));
    }

    @Test
    @DisplayName("[실패] 최대 인원을 초과하는 수강생 목록을 생성한다.")
    void 최대_인원_초과_생성() {
        List<NsUser> users = List.of(nsUser());

        assertThatExceptionOfType(ExceedSessionCapacityException.class)
                .isThrownBy(() -> sessionCapacity(0, users));
    }

    @Test
    @DisplayName("[성공] 최대 인원을 초과하지 않는 수강생 목록을 관리한다.")
    void 최대_인원_미만() {
        NsUser user = nsUser();

        SessionCapacity capacity = sessionCapacity(MAX_CAPACITY);
        assertThatNoException()
                .isThrownBy(() -> capacity.addUser(user));
    }

    @Test
    @DisplayName("[실패] 최대 인원을 초과하는 경우 ExceedSessionCapacityException 예외가 발생한다.")
    void 최대_인원_초과() {
        NsUser user = nsUser();

        SessionCapacity capacity = sessionCapacity(0);
        assertThatExceptionOfType(ExceedSessionCapacityException.class)
                .isThrownBy(() -> capacity.addUser(user));
    }

}
