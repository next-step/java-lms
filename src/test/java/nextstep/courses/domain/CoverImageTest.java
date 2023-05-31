package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class CoverImageTest {
    @Test
    @DisplayName("정상적인 커버이미지 생성")
    void test01() {
        //given //when
        CoverImage coverImage = new CoverImage(1L, "강의1사진", "http://");

        //then
        assertSoftly(softly -> {
            Assertions.assertThat(coverImage.getTitle()).isEqualTo("강의1사진");
            Assertions.assertThat(coverImage.getImageUrl()).isEqualTo("http://");
        });

    }
}
