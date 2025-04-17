package nextstep.sessions.domain;

public enum ImageType {
    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType from(String type) {
        try {
            return ImageType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("지원하지 않는 이미지 타입입니다.");
        }
    }
}
