package nextstep.session.domain;

import nextstep.BaseTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class SessionCoverImage {

    private Long sessionId;
    private String imageName;
    private SessionImageType imageType;
    private URL url;
    private BaseTime baseTime;

    public SessionCoverImage(SessionCoverImage image) {
        this(image.getSessionId(), image.getImageName(), image.getImageType(), image.getUrl().toString());
    }

    public SessionCoverImage(Long sessionId, String imageName, String url, int maxWidth, int maxHeight) {
        this(sessionId, imageName, SessionImageType.of(url), url);
        isValidSize(maxWidth, maxHeight);
    }

    public SessionCoverImage(Long sessionId, String imageName, SessionImageType imageType, String url) {
        this.sessionId = sessionId;
        this.imageType = imageType;
        this.imageName = imageName;
        this.url = createURL(url);
        this.baseTime = new BaseTime();
    }


    private boolean isValidSize(int maxWidth, int maxHeight) {
        try {
            BufferedImage image = ImageIO.read(this.url);
            int width = image.getWidth();
            int height = image.getHeight();
            return width <= maxWidth && height <= maxHeight;
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지의 크기가 큽니다");
        }
    }

    private URL createURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return url;
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("유효하지 않은 이미지 URI입니다");
        }
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getImageName() {
        return imageName;
    }

    public SessionImageType getImageType() {
        return imageType;
    }

    public URL getUrl() {
        return url;
    }

    public BaseTime getBaseTime() {
        return baseTime;
    }

    @Override
    public String toString() {
        return "SessionCoverImage{" +
                "sessionId=" + sessionId +
                ", imageName='" + imageName + '\'' +
                ", imageType=" + imageType +
                ", url=" + url +
                ", baseTime=" + baseTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCoverImage that = (SessionCoverImage) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(imageName, that.imageName) && imageType == that.imageType && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, imageName, imageType, url);
    }
}
