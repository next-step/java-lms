package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    public static final SessionStatus RECRUITING = new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
    public static final SessionStatus NON_RECRUITMENT = new SessionStatus(SessionProgressStatus.END, SessionRecruitmentStatus.NON_RECRUITMENT);

    @DisplayName("강의 진행 상태와 강의 모집 상태를 전달하면 SessionStatus 객체를 생성한다.")
    @Test
    void sessionStatusTest() {
        assertThat(new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING)).isInstanceOf(SessionStatus.class);
    }

    @DisplayName("모집중이지 않은 강의는 true를 반환한다.")
    @Test
    void checkNonRecruitTest() {
        SessionStatus sessionStatus = new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NON_RECRUITMENT);

        assertThat(sessionStatus.isNotRecruiting()).isTrue();
    }

    @DisplayName("모집중인 강의는 false를 반환한다.")
    @Test
    void checkRecruitTest() {
        SessionStatus sessionStatus1 = new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        SessionStatus sessionStatus2 = new SessionStatus(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING);

        assertThat(sessionStatus1.isNotRecruiting()).isFalse();
        assertThat(sessionStatus2.isNotRecruiting()).isFalse();
    }
}
