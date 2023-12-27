package nextstep.session.domain;

public class Image {

    private String imageName;

    private ImageSize imageSize;

    private ImageType imageType;

    private ImageDimensions imageDimensions;

    public Image() {

    }

    public Image(String imageName, long imageSize, String imageType, ImageDimensions imageDimensions) {
        this.imageName = imageName;
        this.imageSize = ImageSize.from(imageSize);
        this.imageType = ImageType.from(imageType);
        this.imageDimensions = imageDimensions;
    }

}
