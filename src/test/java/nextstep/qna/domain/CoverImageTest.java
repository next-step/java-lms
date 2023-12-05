package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoverImageTest {

    @Test
    void height_오류() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> CoverImage.of("test1", 1024 * 1023, "jpg", Pixel.of(300), Pixel.of(250)),
                "높이는 200보다 클 수 없습니다.");
    }

    @Test
    void width_오류() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> CoverImage.of("test1", 1024 * 1023, "jpg", Pixel.of(350), Pixel.of(200)),
                "너비는 300보다 클 수 없습니다.");
    }

    @Test
    void ratio_오류() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> CoverImage.of("test1", 1024 * 1023, "jpg", Pixel.of(250), Pixel.of(150)),
                "비율이 맞지 않습니다.");
    }

    @Test
    void type_오류() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> CoverImage.of("test1", 1024 * 1023, "zip", Pixel.of(300), Pixel.of(200)),
                "이미지 타입이 맞지 않습니다.");
    }

    @Test
    void size_오류() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> CoverImage.of("test1", 1024 * 1028, "jpg", Pixel.of(300), Pixel.of(200)),
                "이미지 사이즈가 맞지 않습니다.");
    }

    @Test
    void 정상_생성() {
        CoverImage coverImage = CoverImage.of("test1", 1024 * 1023, "jpg", Pixel.of(300), Pixel.of(200));
        assertEquals(coverImage.getUrl(), "test1");
    }
}