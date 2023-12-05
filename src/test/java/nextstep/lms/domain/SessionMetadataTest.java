package nextstep.lms.domain;

import nextstep.lms.enums.SessionStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionMetadataTest {
    @DisplayName("강의 상태가 모집중이 아닐경우 예외발생")
    @Test
    void 강의_상태_확인() {
        SessionMetadata completedSession = new SessionMetadata(
                new CoverImage(1l, FileNameStructureTest.NORMAL_FILE_NAME, FileMetadataTest.NORMAL_FILE_METADATA),
                SessionStatusEnum.COMPLETED);
        assertThatThrownBy(() -> completedSession.sessionStatusCheck())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모집중이 아닙니다.");
    }
}