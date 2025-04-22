package nextstep.sessions.domain;

import nextstep.utils.Image;

import java.util.List;

public class CoverImage {
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png", "gif", "svg");
    private final Image image;

    public CoverImage(Image image) {
        validate(image);
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    private void validate(Image image) {
        validateSize(image);
        validateExtension(image);
        validateShape(image);
    }

    public String getPath() {
        return image.getPath();
    }

    private void validateSize(Image image) {
        if (image.size() / 1024.0 / 1024.0 > 1) {
            throw new IllegalArgumentException("파일 용량이 1MB를 초과합니다.");
        }
    }

    private void validateExtension(Image image) {
        if (!ALLOWED_EXTENSIONS.contains(image.extension())) {
            throw new IllegalArgumentException("허용되지 않는 파일 형식입니다.");
        }
    }

    private void validateShape(Image image) {
        if (image.width() < 300) {
            throw new IllegalArgumentException("이미지 너비는 300px 이상이어야 합니다.");
        }
        if (image.height() < 200) {
            throw new IllegalArgumentException("이미지 높이는 200px 이상이어야 합니다.");
        }
        if (image.width() / 3 != image.height() / 2) {
            throw new IllegalArgumentException("이미지 비율이 3:2가 아닙니다.");
        }
    }
}
