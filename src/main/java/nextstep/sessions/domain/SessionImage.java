package nextstep.sessions.domain;

public class SessionImage {
    /*
    강의 커버 이미지
    이미지 크기, 타입을 관리한다.
    이미지는 크기와 타입의 제한이 있다.
     */

    private Long id;

    private ImageSize imageSize;

    private ImageType type;

    public SessionImage(ImageSize imageSize, String type) {
        this.imageSize = imageSize;
        this.type = ImageType.from(type);
    }

    public SessionImage(Long id, ImageSize imageSize, ImageType type) {
        this.id = id;
        this.imageSize = imageSize;
        this.type = type;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public ImageType getType() {
        return type;
    }
}
