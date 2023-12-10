package nextstep.lms.domain;

import nextstep.lms.enums.SessionProgressEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionMetadataTest {
    @DisplayName("강의 상태가 모집중이 아닐경우 예외발생")
    @Test
    void 강의_상태_확인() {
        SessionMetadata completedSession = new SessionMetadata(
                new CoverImage(1L, FileNameStructureTest.NORMAL_FILE_NAME, FileMetadataTest.NORMAL_FILE_METADATA),
                SessionProgressEnum.COMPLETED);
        assertThatIllegalArgumentException().isThrownBy(() -> completedSession.sessionStatusCheck())
                .withMessage("모집중이 아닙니다.");
    }
}