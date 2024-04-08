package nextstep.sessions.domain;

public class Image {

    public static final int MAX_BYTE = 1000;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    private final int imageByte;
    private final ImageType imageType;
    private final int imageWidth;
    private final int imageHeight;

    public Image(int imageByte, String imageType, int imageWidth, int imageHeight) {
        validateImageByte(imageByte);
        this.imageByte = imageByte;

        this.imageType = ImageType.valueOfType(imageType);

        validateImageWidth(imageWidth);
        validateImageHeight(imageHeight);
        validateImageRate(imageWidth, imageHeight);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    private void validateImageByte(int imageByte) {
        if (imageByte > MAX_BYTE) {
            throw new IllegalArgumentException("이미지 크기는 1MB보다 작아야 합니다.");
        }
    }

    private void validateImageWidth(int imageWidth) {
        if (imageWidth < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지 너비는 300픽셀보다 커야 합니다.");
        }
    }

    private void validateImageHeight(int imageHeight) {
        if (imageHeight < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지 높이는 200픽셀보다 커야 합니다.");
        }
    }

    private void validateImageRate(int imageWidth, int imageHeight) {
        double imageRate = (double) imageWidth / imageHeight;
        double imageRateRule = (double) MIN_WIDTH / MIN_HEIGHT;
        if (imageRate != imageRateRule) {
            throw new IllegalArgumentException("이미지 비율은 3대2여야 합니다.");
        }
    }
}
