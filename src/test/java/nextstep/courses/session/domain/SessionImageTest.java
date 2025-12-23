package nextstep.courses.session.domain;


import static nextstep.courses.session.domain.SessionImage.MB;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class SessionImageTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 강의이미지_저장소주소가_없으면_예외처리_할_수_있다(String wrongUrl) {

        assertThatThrownBy(
                () -> new SessionImage(wrongUrl, 1024, "name.jpg", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, MB + 1})
    void 강의이미지가_1MB_이상이면_예외처리_할_수_있다(long wrongSize) {
        assertThatThrownBy(
                () -> new SessionImage("url", wrongSize, "name.jpg", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "name.", "name.txt", "name.pdf", "name.mp3", "name.smi"})
    void 강의이미지_확장자가_정해진_확장자가_아니면_예외처리_할_수_있다(String name) {
        assertThatThrownBy(
                () -> new SessionImage("url", 1024, name, 300, 200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의이미지의_너비가_300픽셀_이하면_예외처리_할_수_있다() {
        int wrongWidth = 299;

        assertThatThrownBy(
                () -> new SessionImage("url", 1024, "name.jpg", wrongWidth, 200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의이미지의_높이가_200픽셀_이하면_예외처리_할_수_있다() {
        int wrongHeight = 199;

        assertThatThrownBy(
                () -> new SessionImage("url", 1024, "name.jpg", 300, wrongHeight)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의이미지의_너비와_높이의_비율이_3대2가_아니면_예외처리_할_수_있다() {
        int wrongWidth = 200;
        int wrongHeight = 100;

        assertThatThrownBy(
                () -> new SessionImage("url", 1024, "name.jpg", wrongWidth, wrongHeight)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}