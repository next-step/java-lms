package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SessionImagesTest {

    @DisplayName("이미지 리스트를 전달하면 SessionImages 객체를 생성한다.")
    @Test
    void sessionImagesTest() {
        assertThat(new SessionImages(List.of(SessionImageTest.IMAGE_JPG, SessionImageTest.IMAGE_PNG))).isInstanceOf(SessionImages.class);
    }

    @DisplayName("빈 리스트를 전달하면 IllegalArgumentException을 던진다.")
    @Test
    void sessionImageExceptionTest() {
        assertThatThrownBy(() -> new SessionImages(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지 객체를 전달하면 이미지를 추가할 수 있다.")
    @Test
    void addImageTest() {
        List<SessionImage> images = new ArrayList<>();
        images.add(SessionImageTest.IMAGE_JPG);
        SessionImages sessionImages = new SessionImages(images);
        int beforeTotalCount = sessionImages.size();
        sessionImages.addImage(SessionImageTest.IMAGE_PNG);

        assertThat(sessionImages.size()).isEqualTo(beforeTotalCount + 1);
    }
}
