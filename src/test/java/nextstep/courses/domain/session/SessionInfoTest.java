package nextstep.courses.domain.session;

import nextstep.courses.RegisterCourseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionInfoTest {
    @Test
    @DisplayName("수강신청시 모집중 상태가 아닐경우 exception")
    void checkCourseStatusToRegister_notRecruiting() {
        final SessionInfo session = new SessionInfo(5, 1, SessionStatusType.IN_PROGRESS, SessionRecruitmentType.NOT_RECRUITING);

        assertThatThrownBy(() -> session.registerSession(1))
                .isInstanceOf(RegisterCourseException.class);
    }

    @Test
    @DisplayName("수강신청시 최대 인원을 초과했을 경우 exception")
    void checkCourseStatusToRegister_overMaxCount() {
        final SessionInfo recruiting = new SessionInfo(5, 1, SessionStatusType.IN_PROGRESS, SessionRecruitmentType.RECRUITING);

        assertThatThrownBy(() -> recruiting.registerSession(5))
                .isInstanceOf(RegisterCourseException.class);
    }

    @Test
    @DisplayName("수강신청시 모집중 상태가 아닐경우 exception")
    void checkIsRegister_notRecruiting() {
        final SessionInfo session = new SessionInfo(5, 1, SessionStatusType.IN_PROGRESS, SessionRecruitmentType.NOT_RECRUITING);

        assertThatThrownBy(() -> session.checkIsRegister(1))
                .isInstanceOf(RegisterCourseException.class);
    }

    @Test
    @DisplayName("수강신청시 최대 인원을 초과했을 경우 exception")
    void checkIsRegister_overMaxCount() {
        final SessionInfo recruiting = new SessionInfo(5, 1, SessionStatusType.IN_PROGRESS, SessionRecruitmentType.RECRUITING);

        assertThatThrownBy(() -> recruiting.registerSession(5))
                .isInstanceOf(RegisterCourseException.class);
    }
}
