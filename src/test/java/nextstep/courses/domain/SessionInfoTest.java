package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nextstep.courses.domain.SessionInfoBuilder.sessionInfo;
import static org.assertj.core.api.Assertions.*;

public class SessionInfoTest {

    private Student student = new Student(1L, 1L);

    @Test
    void 수강신청_정보() {
        SessionInfo sessionInfo = sessionInfo().build();

        assertThat(sessionInfo.getMaxUser()).isNotNull();
        assertThat(sessionInfo.getLectureStatus()).isNotNull();
        assertThat(sessionInfo.getStudents()).isNotNull();
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

        assertThatCode(() -> sessionInfo.register(student))
                .doesNotThrowAnyException();
    }

    @Test
    void 준비중일때_수강신청() {
        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(LectureStatus.PREPARING)
                .build();

        assertThatThrownBy(() -> sessionInfo.register(student))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 모집중일때 가능합니다.");
    }

    @Test
    void 최대수강인원_초과불가() {
        SessionInfo sessionInfo = sessionInfo()
                .lectureStatus(LectureStatus.RECRUITING)
                .maxUser(2)
                .build();

        sessionInfo.register(student);
        sessionInfo.register(student);

        assertThatThrownBy(() -> sessionInfo.register(student))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");

    }
}
