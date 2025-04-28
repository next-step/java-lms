package nextstep.courses.domain.session;

import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionThumbnailTest {

    @Test
    @DisplayName("썸네일을 생성한다")
    void create() {
        SessionThumbnail thumbnail = new SessionThumbnail();
        thumbnail.addThumbnail("test.jpg", 1024L, 300, 200);
        
        assertThat(thumbnail.getThumbnails()).hasSize(1);
    }

    @Test
    @DisplayName("썸네일은 최대 5개까지 등록할 수 있다")
    void maxThumbnailCount() {
        SessionThumbnail thumbnail = new SessionThumbnail();
        for (int i = 0; i < 5; i++) {
            thumbnail.addThumbnail("test" + i + ".jpg", 1024L, 300, 200);
        }

        assertThatThrownBy(() -> thumbnail.addThumbnail("test6.jpg", 1024L, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("썸네일은 최대 5개까지만 등록할 수 있습니다.");
    }

    @Test
    @DisplayName("썸네일 파일 크기는 1MB를 초과할 수 없다")
    void maxFileSize() {
        SessionThumbnail thumbnail = new SessionThumbnail();
        
        assertThatThrownBy(() -> thumbnail.addThumbnail("test.jpg", 1024 * 1024 + 1, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("썸네일 파일 크기는 1MB를 초과할 수 없습니다.");
    }
} 