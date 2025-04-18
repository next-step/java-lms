package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SessionBaseInfoTest {

    @Test
    @DisplayName("세션 상태가 PREPARING일 때 open 호출하면 상태가 RECRUITING으로 변경된다")
    void open_changesStateToRecruiting_whenStateIsPreparing() {
        LocalDate startAt = LocalDate.of(2025, 1, 1);
        LocalDate endAt = LocalDate.of(2025, 1, 31);
        CoverImage coverImage = CoverImage.defaultImage();
        SessionBaseInfo sessionBaseInfo = new SessionBaseInfo(startAt, endAt, coverImage);

        sessionBaseInfo.open();

        Assertions.assertThat(sessionBaseInfo.getState()).isEqualTo(SessionState.RECRUITING);
    }

    @Test
    @DisplayName("세션 상태가 PREPARING이 아닐 때 open 호출하면 예외가 발생한다")
    void open_throwsException_whenStateIsNotPreparing() {
        LocalDate startAt = LocalDate.of(2025, 1, 1);
        LocalDate endAt = LocalDate.of(2025, 1, 31);
        CoverImage coverImage = CoverImage.defaultImage();
        SessionBaseInfo sessionBaseInfo = new SessionBaseInfo(startAt, endAt, coverImage);

        sessionBaseInfo.open();

        Assertions.assertThatThrownBy(sessionBaseInfo::open)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비중 상태가 아닙니다.");
    }

    @Test
    @DisplayName("세션 상태가 RECRUITING일 때 close 호출하면 상태가 CLOSED로 변경된다")
    void close_changesStateToClosed_whenStateIsRecruiting() {
        LocalDate startAt = LocalDate.of(2025, 1, 1);
        LocalDate endAt = LocalDate.of(2025, 1, 31);
        CoverImage coverImage = CoverImage.defaultImage();
        SessionBaseInfo sessionBaseInfo = new SessionBaseInfo(startAt, endAt, coverImage);

        sessionBaseInfo.open();
        sessionBaseInfo.close();

        Assertions.assertThat(sessionBaseInfo.getState()).isEqualTo(SessionState.CLOSED);
    }

    @Test
    @DisplayName("세션 상태가 RECRUITING이 아닐 때 close 호출하면 예외가 발생한다")
    void close_throwsException_whenStateIsNotRecruiting() {
        LocalDate startAt = LocalDate.of(2025, 1, 1);
        LocalDate endAt = LocalDate.of(2025, 1, 31);
        CoverImage coverImage = CoverImage.defaultImage();
        SessionBaseInfo sessionBaseInfo = new SessionBaseInfo(startAt, endAt, coverImage);

        Assertions.assertThatThrownBy(sessionBaseInfo::close)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중 상태가 아닙니다.");
    }
}