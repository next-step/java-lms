package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionParticipantsTest {

    @DisplayName("세션 참가자를 추가한다.")
    @Test
    void add() {
        List<NsUser> participants = new ArrayList<>() {{
            add(NsUserTest.JAVAJIGI);
        }};
        SessionParticipants sessionParticipants = new SessionParticipants(participants);
        sessionParticipants.add(NsUserTest.SANJIGI);
        assertEquals(2, sessionParticipants.count());
    }

    @DisplayName("이미 등록된 사용자는 예외가 발생한다.")
    @Test
    void addException() {
        List<NsUser> participants = new ArrayList<>() {{
            add(NsUserTest.JAVAJIGI);
        }};
        SessionParticipants sessionParticipants = new SessionParticipants(participants);
        assertThatThrownBy(() -> sessionParticipants.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}