package nextstep.courses.domain.image.constant;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType fromName(String name){
        for (ImageType type : ImageType.values()) {
            if(type.name().equals(name)){
                return type;
            }
        }
        throw new IllegalArgumentException("이미지 타입을 다시 확인해주세요.");
    }
}
