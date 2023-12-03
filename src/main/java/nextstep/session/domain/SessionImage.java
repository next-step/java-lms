package nextstep.session.domain;

public class SessionImage {
    private static final int MAXIMUM_IMAGE_SIZE = 1024;
    public static final int MINIMUM_WIDTH_PIXEL = 300;
    public static final int MINIMUM_HEIGHT_PIXEL = 200;

    private String imageURL;
    private int imageSize;

    private ImageType imageType;

    private int width;

    private int height;

    public SessionImage(String imageURL, int imageSize, ImageType imageType, int width, int height) {
        this.imageURL = imageURL;
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    public static SessionImage of(String imageURL, int imageSize, ImageType imageType, int width, int height) {
        validate(imageSize, width, height);
        return new SessionImage(imageURL, imageSize, imageType, width, height);
    }

    private static void validate(int imageSize, int width, int height) {
        validateSize(imageSize);
        validateWidthHeight(width, height);
    }

    private static void validateSize(int imageSize) {
        if (imageSize > MAXIMUM_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB이하여야 합니다.");
        }
    }

    private static void validateWidthHeight(int width, int height) {
        if (width < MINIMUM_WIDTH_PIXEL) {
            throw new IllegalArgumentException("width은 최소 300px이상이여야합니다.");
        }
        if (height < MINIMUM_HEIGHT_PIXEL) {
            throw new IllegalArgumentException("height은 최소 200px이상이여야합니다.");
        }
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2여야 합니다.");
        }
    }
}
