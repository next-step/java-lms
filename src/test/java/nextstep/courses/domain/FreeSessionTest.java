package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionProgressStatus;
import nextstep.courses.domain.type.SessionRecruitingStatus;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreeSessionTest {

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다")
    public void session_image() {
        assertThat(new FreeSession(duration(), images())).extracting(Session::images).isEqualTo(images());
    }

    @Test
    @DisplayName("강의 상태가 모집중이 아닐 때 수강신청 시 에러 발생한다")
    public void not_recruiting_status_apply() {
        Session session = new FreeSession(duration(), images(), notRecruitingStatus());

        assertThatExceptionOfType(NotRecruitingSessionException.class)
            .isThrownBy(() -> session.apply(null, NsUserTest.JAVAJIGI))
            .withMessageMatching("모집중인 강의가 아닙니다.");
    }

    @ParameterizedTest
    @MethodSource("sessionStatus")
    @DisplayName("수강 신청할 수 있다")
    public void apply_session(SessionStatus status) {
        Session session = new FreeSession(duration(), images(), status);
        NsUser user = NsUserTest.JAVAJIGI;

        session.apply(null, user);
        Assertions.assertThat(session.applys()).isEqualTo(Arrays.asList(new Apply(session, user)));
    }

    private Duration duration() {
        return new Duration(LocalDate.now(), LocalDate.now());
    }

    private Images images() {
        Image image = new Image(1, "JPG", 300, 200);
        return new Images(Arrays.asList(image));
    }

    private static SessionStatus notRecruitingStatus() {
        return new SessionStatus(SessionProgressStatus.TERMINATE, SessionRecruitingStatus.NOT_RECRUITING);
    }

    private static Stream<Arguments> sessionStatus() {
        return Stream.of(
            Arguments.of(new SessionStatus(SessionProgressStatus.READY, SessionRecruitingStatus.RECRUITING)),
            Arguments.of(new SessionStatus(SessionProgressStatus.ONGOING, SessionRecruitingStatus.RECRUITING))
        );
    }
}
