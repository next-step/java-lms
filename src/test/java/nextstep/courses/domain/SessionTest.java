package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;
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

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @ValueSource(strings={"https//nextstep.com", "nextstep.com", "abcvdjfn", "httb://nextstep.com"})
    void 이미지URL형식검증(String url) {
        assertThatThrownBy(() -> {
            new Session("20230701", "20230731", url, true, 30);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("입력된 이미지의 URL 형식이 올바르지 않습니다.");
    }

    @Test
    void 강의상태변경_불가() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);
        Session session =  new Session(sessionDate,
                "https://nextstep.com", true ,30);

        assertThatThrownBy(() -> {
            session.setSessionState(SessionState.END);
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("강의종료일이 경과하여 상태 변경이 불가합니다.");
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
            session.signUpStudent("jerry");
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("강의가 모집중이 아니어서 신청이 불가합니다.");
    }

    @Test
    void 강의신청테스트() {
        Session session =  new Session("20230701", "20230731", "https://nextstep.com", true ,30);

        session.setSessionState(SessionState.PROCEEDING);

        session.signUpStudent("jerry");
        session.signUpStudent("joy");
        session.signUpStudent("david");

        Assertions.assertThat(session.getSignedUpStatus()).isEqualTo(3);
    }
}
