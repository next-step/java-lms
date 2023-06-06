package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionRecruitmentStatusTest {
    @Test
    @DisplayName("강의 모집 상태가 모집중인 경우 수강신청 가능하다")
    void recruiting_status() {
        SessionRecruitmentStatus recruiting = SessionRecruitmentStatus.RECRUITING;
        assertThat(recruiting.canEnroll()).isTrue();
    }

    @Test
    void not_recruiting_status() {
        SessionRecruitmentStatus notRecruiting = SessionRecruitmentStatus.NOT_RECRUITING;
        assertThat(notRecruiting.canEnroll()).isFalse();
    }
}
