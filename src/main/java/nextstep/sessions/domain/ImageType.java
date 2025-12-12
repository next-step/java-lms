package nextstep.sessions.domain;

public enum ImageType {
    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType from(String ext) {
        if (ext == null || ext.trim().isEmpty()) {
            throw new IllegalArgumentException("확장자가 유효하지 않습니다");
        }
        for (ImageType type : ImageType.values()) {
            if (type.name().equalsIgnoreCase(ext)) {
                return type;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다: " + ext);
    }
}

