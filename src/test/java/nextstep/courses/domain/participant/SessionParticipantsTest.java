package nextstep.courses.domain.participant;

import nextstep.courses.exception.AlreadyRegisterUserException;
import nextstep.courses.type.SessionSubscriptionStatus;
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
        List<SessionUserEnrolment> participants = new ArrayList<>() {{
            add(new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING));
        }};
        SessionParticipants sessionParticipants = new SessionParticipants(participants);
        SessionUserEnrolment sessionUserEnrolment = new SessionUserEnrolment(2L, 1L, SessionSubscriptionStatus.WAITING);
        sessionParticipants.add(sessionUserEnrolment);
        assertEquals(2, sessionParticipants.count());
    }

    @DisplayName("이미 등록된 사용자는 예외가 발생한다.")
    @Test
    void addException() {
        List<SessionUserEnrolment> participants = new ArrayList<>() {{
            add(new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING));
        }};
        SessionParticipants sessionParticipants = new SessionParticipants(participants);
        assertThatThrownBy(() -> sessionParticipants.add(new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING)))
                .isInstanceOf(AlreadyRegisterUserException.class);
    }
}