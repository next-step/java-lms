package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SessionStatusTest {
    @Test
    @DisplayName("강의 상태가 신청 가능하면 수강신청이 가능하다")
    void enroll() {
        SessionStatus sessionStatus = new SessionStatus(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING);
        assertThat(sessionStatus.canEnroll()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("notRecruiting")
    void not_enroll(SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        SessionStatus sessionStatus = new SessionStatus(progressStatus, recruitmentStatus);
        assertThat(sessionStatus.canEnroll()).isFalse();
    }

    public static Stream<Arguments> notRecruiting() {
        return Stream.of(
                arguments(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING),
                arguments(SessionProgressStatus.END, SessionRecruitmentStatus.NOT_RECRUITING),
                arguments(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.NOT_RECRUITING),
                arguments(SessionProgressStatus.RECRUITING, SessionRecruitmentStatus.NOT_RECRUITING),
                arguments(SessionProgressStatus.END, SessionRecruitmentStatus.RECRUITING),
                arguments(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING)
        );
    }
}
