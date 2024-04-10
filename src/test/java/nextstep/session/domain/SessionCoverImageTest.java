package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.session.InvalidImageConditionsException;
import org.junit.jupiter.api.Test;

class SessionCoverImageTest {

    @Test
    public void 이미지는_최소_너비와_높이를_충족해야_한다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(100, 180, 10000, "png");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("이미지는 최소 300*200 이상이여야합니다. 입력된 사이즈 100*180");
    }

    @Test
    public void 이미지는_1MB를_초과할_수_없다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(350, 250, 10000000, "png");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("이미지 크기는"
                + "1MB 이하여야합니다. 입력된 이미지 크기 9MB");
    }

    @Test
    public void 이미지는_너비와_높이가_3대2_이어야_한다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(350, 300, 10000, "png");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("너비와 높이가 3:2 비율이여야 합니다.");
    }

    @Test
    public void 이미지는_정해진_확장자만_추가_가능하다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(900, 600, 10000, "BMP");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("명시된 확장자만 추가 가능합니다.gif, jpg, jpeg, png, svg");
    }

    @Test
    public void 이미지_생성() throws InvalidImageConditionsException {
        SessionCoverImage coverImage = new SessionCoverImage(900, 600, 10000, "png");
        assertThat(coverImage.getImageType()).isEqualTo(ImageType.png);
    }
}
