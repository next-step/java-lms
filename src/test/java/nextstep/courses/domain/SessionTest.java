package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("세션에 추가된 CoverImageInfo가 있으면 False")
    void addCoverImageInfo() {
        // given
        Session session = FreeSession.createNewInstance(new Course(), SessionInfos.createWithDefault(SessionDate.of(LocalDateTime.now(), LocalDateTime.now())));
        CoverImageInfo coverImageInfo = CoverImageInfo.createNewInstance(1000L, "jpg", 300L, 200L);
        // when
        session.addCoverImageInfo(coverImageInfo);

        assertThat(session.isCoverImageEmpty()).isFalse();
    }
}
