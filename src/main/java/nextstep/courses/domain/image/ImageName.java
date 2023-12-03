package nextstep.courses.domain.image;

public class ImageName {

    private static final String IMAGE_EXTENSION_REGEX = "^.*\\.(gif|jpe?g|png|svg)$";
    private final String name;

    public ImageName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches(IMAGE_EXTENSION_REGEX)) {
            throw new IllegalArgumentException("이미지 확장자를 확인하세요.");
        }
    }

    public String name() {
        return name;
    }
}
