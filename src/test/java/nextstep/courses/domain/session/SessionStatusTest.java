package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionProgressStatusTest {
    @Test
    @DisplayName("강의 진행 상태를 확인한다")
    void isInProgress() {
        // given
        SessionProgressStatus preparing = SessionProgressStatus.PREPARING;
        SessionProgressStatus inProgress = SessionProgressStatus.IN_PROGRESS;
        SessionProgressStatus ended = SessionProgressStatus.CLOSED;

        // when & then
        assertThat(preparing.isInProgress()).isFalse();
        assertThat(inProgress.isInProgress()).isTrue();
        assertThat(ended.isInProgress()).isFalse();
    }
}

class SessionRecruitmentStatusTest {
    @Test
    @DisplayName("모집중인 강의인지 확인한다")
    void isRecruiting() {
        // given
        SessionRecruitmentStatus recruiting = SessionRecruitmentStatus.RECRUITING;
        SessionRecruitmentStatus notRecruiting = SessionRecruitmentStatus.NOT_RECRUITING;

        // when & then
        assertThat(recruiting.isRecruiting()).isTrue();
        assertThat(notRecruiting.isRecruiting()).isFalse();
    }
} 