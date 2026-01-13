package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CoverImageTest {
    @Test
    public void create() {
        assertThat(new CoverImage("file.png", 300, 200, 1024 * 1024)).isEqualTo(new CoverImage("file.png", ImageType.PNG, new ImageDimension(300, 200), new ImageSize(1024 * 1024)));
    }
}
