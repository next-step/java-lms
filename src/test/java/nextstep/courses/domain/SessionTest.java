package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
    private static final NsUser joy = new NsUser(2L, "joy", "password", "조이", "joy@nextstep.com");
    private static final NsUser david = new NsUser(3L, "david","password","데이빗","david@gamil.com");

    @Test
    void 생성자검증() {
        Assertions.assertThat(new Session("20230701", "20230731", 10 ,0L))
                .isInstanceOf(Session.class);
    }

    @Test
    void 강의상태변경_불가() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);
        Session session =  new Session(0L, sessionDate, "https://nextstep.com", true ,SessionState.PREPARING
                ,30, 0L, LocalDateTime.now(), LocalDateTime.now());

        assertThatThrownBy(() -> {
            session.setSessionState(SessionState.END);
        }).isInstanceOf(SessionExpiredException.class).hasMessageContaining("강의종료일이 경과하여 상태 변경이 불가합니다.");
    }

    @Test
    void 강의상태변경() {
        Session session =  new Session("20230701", "20230731", 30 ,1L);

        session.setSessionState(SessionState.PREPARING);
        Assertions.assertThat(session.equalsState(SessionState.PREPARING)).isTrue();
    }

    @Test
    void 강의신청불가_모집중아님() {
        Session session =  new Session("20230701", "20230731", 30 ,1L);

        session.setSessionState(SessionState.END);

        assertThatThrownBy(() -> {
            session.enroll(new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com"));
        }).isInstanceOf(SessionNotOpenException.class).hasMessageContaining("강의가 모집중이 아니어서 신청이 불가합니다.");
    }

    @Test
    void 강의신청테스트() {
        Session session =  new Session("20230701", "20230731", 30 ,1L);

        session.setSessionState(SessionState.PROCEEDING);

        session.enroll(jerry);
        session.enroll(joy);
        session.enroll(david);

        Assertions.assertThat(session.getSignedUpStatus()).isEqualTo(3);
    }
}
