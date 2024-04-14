package nextstep.courses.domain;

public class Image {

    private ImageType imageType;
    private ImageSize imageSize;
    private ImageRate imageRate;

    public Image() {
    }

    public Image(ImageType imageType, ImageSize imageSize, ImageRate imageRate) {
        this.imageType = imageType;
        this.imageSize = imageSize;
        this.imageRate = imageRate;
    }

}
