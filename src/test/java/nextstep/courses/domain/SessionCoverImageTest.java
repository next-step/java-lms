package nextstep.courses.domain;

import nextstep.courses.domain.fixture.SessionCoverImageFixture;
import nextstep.courses.exception.SessionCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;
import static nextstep.courses.exception.SessionExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionCoverImageTest {

    @Test
    @DisplayName("[성공][경계값] 강의 커버 이미지 정보를 생성한다.")
    void 강의_커버_이미지() {
        assertThatNoException()
                .isThrownBy(SessionCoverImageFixture::coverImage);
    }

    @Test
    @DisplayName("[실패] 이미지 크기를 초과한 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_크기_초과() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> coverImage(1024*1025))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("[실패] 이미지 가로 x 세로 비율이 일치하지 않는 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_비율() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> coverImage(400, 300))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("[실패] 지원하지 않는 이미지 확장자인 경우 SessionCoverImageException 예외가 발생한다.")
    void 강의_커버_이미지_확장자() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> coverImage("pdf"))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }
}
