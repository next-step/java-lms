package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nextstep.courses.domain.SessionInfoBuilder.sessionInfo;
import static org.assertj.core.api.Assertions.*;

public class SessionInfoTest {

    @Test
    void 수강신청_정보() {
        SessionInfo sessionInfo = sessionInfo().build();

        assertThat(sessionInfo.getMaxUser()).isNotNull();
        assertThat(sessionInfo.getLectureStatus()).isNotNull();
        assertThat(sessionInfo.getUsers()).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"PREPARING", "RECRUITING", "COMPLETED"})
    void 수강신청_상태(String input) {
        LectureStatus lectureStatus = LectureStatus.valueOf(input);

        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(lectureStatus)
                .build();

        assertThat(sessionInfo.getLectureStatus()).isEqualTo(lectureStatus);
    }

    @Test
    void 모집중일때_수강신청() {
        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(LectureStatus.RECRUITING)
                .build();

        assertThatCode(() -> sessionInfo.register(NsUser.GUEST_USER))
                .doesNotThrowAnyException();
    }

    @Test
    void 준비중일때_수강신청() {
        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(LectureStatus.PREPARING)
                .build();

        assertThatThrownBy(() -> sessionInfo.register(NsUser.GUEST_USER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 모집중일때 가능합니다.");
    }

    @Test
    void 최대수강인원_초과불가() {
        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(LectureStatus.RECRUITING)
                .maxUser(2)
                .build();

        sessionInfo.register(NsUser.GUEST_USER);
        sessionInfo.register(NsUser.GUEST_USER);

        assertThatThrownBy(() -> sessionInfo.register(NsUser.GUEST_USER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");

    }
}
