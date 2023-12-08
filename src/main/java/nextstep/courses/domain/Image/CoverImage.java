package nextstep.courses.domain.Image;

import nextstep.courses.domain.Image.ImageSize;
import nextstep.courses.domain.Image.ImageType;
import nextstep.courses.domain.Image.ImgaePixel;

public class CoverImage {

    private Long id;

    private ImageSize imageSize;

    private ImgaePixel imgaePixel;

    private ImageType imageType;

    public CoverImage(Long id, ImageSize imageSize, ImgaePixel imgaePixel, ImageType imageType) {
        this.id = id;
        this.imageSize = imageSize;
        this.imgaePixel = imgaePixel;
        this.imageType = imageType;
    }
}
