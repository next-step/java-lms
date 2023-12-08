package nextstep.image.dto;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageCapacity;
import nextstep.image.domain.ImageSize;
import nextstep.image.domain.ImageType;

public class CreateImageRequest {

    private long value;
    private String capacityTypeName;
    private ImageType imageType;
    private long width;
    private long height;

    public Image toEntity() {
        return new Image(
                new ImageCapacity(value, capacityTypeName),
                imageType,
                new ImageSize(width, height)
        );
    }
}
