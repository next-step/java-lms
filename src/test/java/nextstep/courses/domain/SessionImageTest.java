package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.type.ImageExtension.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImageTest {
    @Test
    @DisplayName("[SessionImage.new] 이미지 크기는 1MB 이하")
    public void sizeTest() {
        assertThatCode(() -> {
            new SessionImage(1024, 300, 200, JPG);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            new SessionImage(1025, 300, 200, JPG);
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("[SessionImage.new] 이미지 width는 300 픽셀 이상")
    public void widthTest() {

        assertThatCode(() -> {
            new SessionImage(50, 300, 200, JPG);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            new SessionImage(50, 299, 200, JPG);
        }).isInstanceOf(Exception.class);
    }
    @Test
    @DisplayName("[SessionImage.new] 이미지 height는 300 픽셀 이상")
    public void heightTest() {

        assertThatCode(() -> {
            new SessionImage(50, 300, 200, JPG);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            new SessionImage(50, 300, 199, JPG);
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("[SessionImage.new] 이미지 비율은 3:2")
    public void ratioTest() {

        assertThatCode(() -> {
            new SessionImage(50, 600, 400, JPG);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            new SessionImage(50, 900, 1000, JPG);
        }).isInstanceOf(Exception.class);
    }
}