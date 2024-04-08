package nextstep.sessions.domain;

public class Image {

    private final int imageByte;
    private final String imageType;
    private final int imageWidth;
    private final int imageHeight;

    public Image(int imageByte, String imageType, int imageWidth, int imageHeight) {
        validateImageByte(imageByte);
        this.imageByte = imageByte;
        this.imageType = imageType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    private void validateImageByte(int imageByte) {
        if (imageByte > 1000) {
            throw new IllegalArgumentException("이미지 크기는 1MB보다 작아야 합니다.");
        }
    }
}
