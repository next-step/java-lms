package nextstep.courses.domain;

import nextstep.courses.domain.fixture.SessionFixture;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.exception.SessionCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.exception.SessionExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CoverImageTest {

    @Test
    @DisplayName("[성공][경계값] 강의 커버 이미지 정보를 생성한다.")
    void 강의_커버_이미지() {
        assertThatNoException()
                .isThrownBy(SessionFixture::coverImage);
    }

    @Test
    @DisplayName("[실패] 이미지 크기를 초과한 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_크기_초과() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new CoverImage(1024 * 1025, 300, 200, "gif"))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("[실패] 이미지 가로 x 세로 비율이 일치하지 않는 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_비율() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new CoverImage(1024 * 1024, 400, 300, "gif"))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("[실패] 지원하지 않는 이미지 확장자인 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_확장자() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new CoverImage(1024 * 1024, 300, 200, "pdf"))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }
}
