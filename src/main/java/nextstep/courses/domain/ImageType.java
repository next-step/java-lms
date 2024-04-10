package nextstep.courses.domain;

public enum ImageType {

    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType of(String type) {
        if (type == null) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg만 허용됩니다.");
        }
        return ImageType.valueOf(type.toUpperCase());
    }
}
