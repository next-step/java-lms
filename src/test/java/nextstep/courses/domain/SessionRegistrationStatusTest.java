package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionRegistrationStatusTest {

    @Test
    void 최대수강인원을_넘길수_없다() {
        assertThatThrownBy(() -> {
            new SessionRegistrationStatus(SessionStatus.OPEN, 2, List.of(new NsUser(), new NsUser(), new NsUser()));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 등록불가한_상태일땐_수강인원을_추가할수없다() {
        SessionRegistrationStatus sessionRegistrationStatus = new SessionRegistrationStatus(SessionStatus.CLOSED, 2, new ArrayList<>());
        assertThatThrownBy(() -> {
            sessionRegistrationStatus.addStudents(List.of(new NsUser()));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
