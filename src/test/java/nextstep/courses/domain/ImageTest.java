package nextstep.courses.domain;

import nextstep.courses.domain.session.image.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageTest {
    public static final Image IMAGE = new Image(1, "gif", 300, 200);

    private int normalSize = 1;
    private String normalType = "gif";
    private int normalWidth = 300;
    private int normalHeight = 200;

    @Test
    void Image_생성자_예외_케이스_크기() {
        int exceptionSize = normalSize + 1;
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> new Image(exceptionSize, normalType, normalWidth, normalHeight)
        );
    }

    @Test
    void image_생성자_예외_케이스_타입() {
        String exceptionType = "temp";
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> new Image(normalSize, exceptionType, normalWidth, normalHeight)
        );
    }

    @Test
    void image_생성자_예외_케이스_가로_크기() {
        int exceptionWidth = normalWidth - 50;
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> new Image(normalSize, normalType, exceptionWidth, normalHeight)
        );
    }

    @Test
    void image_생성자_예외_케이스_세로_크기() {
        int exceptionHeight = normalHeight - 50;
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> new Image(normalSize, normalType, normalWidth, exceptionHeight)
        );
    }

    @Test
    void image_생성자_예외_케이스_가로_세로_비율() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> new Image(normalSize, normalType, normalWidth + 50, normalHeight)
        );
    }
}
