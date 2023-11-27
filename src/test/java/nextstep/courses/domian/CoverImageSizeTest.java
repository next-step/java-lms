package nextstep.courses.domian;

import nextstep.courses.CannotRecruitException;
import nextstep.courses.domain.CoverImageSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageSizeTest {

    @Test
    @DisplayName("정상적인 범위의 이미지 사이즈일 경우 정상적으로 생성된다.")
    void createCoverImageSize() {
        CoverImageSize coverImageSize = new CoverImageSize(1025);

        assertThat(coverImageSize).isEqualTo(new CoverImageSize(1025));
    }

    @ParameterizedTest(name = "이미지 사이즈가 {0}경우 오류가 발샌한다.")
    @ValueSource(ints = {-1, 0})
    void createCoverImageSize_0_negative(int imageSize) {
        assertThatThrownBy(() -> new CoverImageSize(imageSize))
                .isInstanceOf(CannotRecruitException.class)
                .hasMessage("이미지 사이즈는 0이거나 0보다작을 수 없습니다.");
    }

    @Test
    @DisplayName("이미지 사이즈가 최대 이미지 사이즈(1MB)보다 클 경우 오류가 발생한다.")
    void createCoverImageSize_overSize() {
        assertThatThrownBy(() -> new CoverImageSize(1048577))
                .isInstanceOf(CannotRecruitException.class)
                .hasMessage("이미지 사이즈는 1MB를 넘을 수 없습니다.");
    }
}
