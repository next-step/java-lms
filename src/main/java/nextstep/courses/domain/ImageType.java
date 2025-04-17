package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {

    GIF("GIF"),
    JPG("JPG"),
    JPEG("JPEG"),
    PNG("PNG"),
    SVG("SVG");

    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }

    public static ImageType getCoverImageType(String imageType) {
        imageType = imageType.toUpperCase();
        isCoverImageType(imageType);
        return valueOf(imageType);
    }

    public String getImageType() {
        return imageType;
    }

    public static boolean isCoverImageType(String imageType) {
        return Arrays.stream(values())
                .anyMatch(type -> type.getImageType().equals(imageType.toUpperCase()));
    }


}
