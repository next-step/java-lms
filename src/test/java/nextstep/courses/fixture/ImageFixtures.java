package nextstep.courses.fixture;

import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageType;
import nextstep.courses.domain.course.session.image.Images;

import java.time.LocalDateTime;
import java.util.List;

public class ImageFixtures {
    public static Image image() {
        return new Image(1000, ImageType.GIF, Image.WIDTH_MIN, Image.HEIGHT_MIN, 1L, LocalDateTime.now());
    }

    public static Images images() {
        return new Images(List.of(image()));
    }
}
