package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {

    @Test
    void 생성자검증() {
        Assertions.assertThat(new Session("20230701", "20230731",
                        "https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/00ca5b6e04194376ad430218dbefba5c", true ,30))
                .isInstanceOf(Session.class);
    }

    @Test
    void 강의상태변경_불가() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);
        Session session =  new Session(sessionDate,
                "https://nextstep.com", true ,30);

        assertThatThrownBy(() -> {
            session.setSessionState(SessionState.END);
        }).isInstanceOf(SessionExpiredException.class).hasMessageContaining("강의종료일이 경과하여 상태 변경이 불가합니다.");
    }

    @Test
    void 강의상태변경() {
        Session session =  new Session("20230701", "20230731", "https://nextstep.com", true ,30);

        session.setSessionState(SessionState.PREPARING);
        Assertions.assertThat(session.equalsState(SessionState.PREPARING)).isTrue();
    }

    @Test
    void 강의신청불가_모집중아님() {
        Session session =  new Session("20230701", "20230731", "https://nextstep.com", true ,30);

        session.setSessionState(SessionState.END);

        assertThatThrownBy(() -> {
            session.signUpStudent(new Student("jerry","제리","jerry@gamil.com"));
        }).isInstanceOf(SessionNotOpenException.class).hasMessageContaining("강의가 모집중이 아니어서 신청이 불가합니다.");
    }

    @Test
    void 강의신청테스트() {
        Session session =  new Session("20230701", "20230731", "https://nextstep.com", true ,30);

        session.setSessionState(SessionState.PROCEEDING);

        session.signUpStudent(new Student("jerry","제리","jerry@gamil.com"));
        session.signUpStudent(new Student("joy","조이","joy@gamil.com"));
        session.signUpStudent(new Student("david","데이빗","david@gamil.com"));

        Assertions.assertThat(session.getSignedUpStatus()).isEqualTo(3);
    }
}
