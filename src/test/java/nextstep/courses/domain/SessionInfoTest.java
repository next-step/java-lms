package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionInfoTest {

    @Test
    void 수강신청_정보() {
        SessionInfo sessionInfo = new SessionInfo(
                LectureStatus.PREPARING,
                List.of(
                        NsUser.GUEST_USER
                ),
                100);

        assertThat(sessionInfo.getMaxUser()).isEqualTo(100);
        assertThat(sessionInfo.getLectureStatus()).isEqualTo(LectureStatus.PREPARING);
        assertThat(sessionInfo.getUsers()).hasSize(1);
    }

    @Test
    void 수강신청_정보_준비중() {
        SessionInfo sessionInfo = new SessionInfo(
                LectureStatus.PREPARING,
                List.of(
                        NsUser.GUEST_USER
                ),
                100);

        assertThat(sessionInfo.getMaxUser()).isEqualTo(100);
        assertThat(sessionInfo.getLectureStatus()).isEqualTo(LectureStatus.PREPARING);
        assertThat(sessionInfo.getUsers()).hasSize(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PREPARING", "RECRUITING", "COMPLETED"})
    void 수강신청_상태(String input) {
        LectureStatus lectureStatus = LectureStatus.valueOf(input);
        SessionInfo sessionInfo = new SessionInfo(
                lectureStatus,
                List.of(
                        NsUser.GUEST_USER
                ),
                100);

        assertThat(sessionInfo.getLectureStatus()).isEqualTo(lectureStatus);
    }

    @Test
    void 모집중일때_수강신청() {
        SessionInfo sessionInfo = new SessionInfo(
                LectureStatus.RECRUITING,
                new ArrayList<>(),
                100);

        assertThatCode(() -> sessionInfo.register(NsUser.GUEST_USER))
                .doesNotThrowAnyException();
    }

    @Test
    void 준비중일때_수강신청() {
        SessionInfo sessionInfo = new SessionInfo(
                LectureStatus.PREPARING,
                new ArrayList<>(),
                100);

        assertThatThrownBy(() -> sessionInfo.register(NsUser.GUEST_USER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 모집중일때 가능합니다.");
    }

    @Test
    void 최대수강인원_초과불가() {
        SessionInfo sessionInfo = new SessionInfo(
                LectureStatus.RECRUITING,
                new ArrayList<>(),
                2);

        sessionInfo.register(NsUser.GUEST_USER);
        sessionInfo.register(NsUser.GUEST_USER);

        assertThatThrownBy(() -> sessionInfo.register(NsUser.GUEST_USER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");

    }
}
