package nextstep.sessions.domain;

public class SessionImage {
    private final SessionImageByte imageByte;
    private final SessionImageName imageType;
    private final SessionImageSize imageSize;

    public SessionImage(int imageByte, String imageName, int imageWidth, int imageHeight) {
        this(new SessionImageByte(imageByte), new SessionImageName(imageName), new SessionImageSize(imageWidth, imageHeight));
    }

    public SessionImage(SessionImageByte imageByte, SessionImageName imageType, SessionImageSize imageSize) {
        this.imageByte = imageByte;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }
}
