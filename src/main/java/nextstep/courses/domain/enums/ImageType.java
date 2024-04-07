package nextstep.courses.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ImageType {
    GIF, JPG, JPEG, PNG, SVG;

    public static Optional<ImageType> findByType(String imageTypeStr) {
        String upperImageTypeStr = imageTypeStr.toUpperCase();
        return Arrays.stream(ImageType.values())
                .filter(imageType -> upperImageTypeStr.equals(imageType.name()))
                .findFirst();
    }
}
