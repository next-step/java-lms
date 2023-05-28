package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ImageTest {

    private final Image compareImage = Image.of(1, "이미지입니다");


    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
    }


    @Test
    @DisplayName("객체가 잘 생성되는지 확인")
    void 객체가_정상적으로_생성되는지_확인() {

        Image image = Image.createImage("이미지 입니다");
        assertAll(
                () -> assertThat(image).isNotNull(),
                () -> assertThat(image).isInstanceOf(Image.class)
        );
    }

    @Test
    @DisplayName("객체 동등성 테스트")
    void 객체가_동등성_테스트() {

        Image image = Image.createImage("이미지 입니다");

        assertAll(
                () -> assertThat(image).isNotNull(),
                () -> assertThat(image).isEqualTo(compareImage)
        );
    }
}