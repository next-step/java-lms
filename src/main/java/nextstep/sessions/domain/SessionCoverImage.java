package nextstep.sessions.domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class SessionCoverImage {

    private Long id;
    private String fileName;
    private SessionImageType fileType;
    private URL url;

    public SessionCoverImage(String fileName, String urlString, int maxWidth, int maxHeight) {
        this.fileName = fileName;
        this.fileType = SessionImageType.of(urlString);
        this.url = createURL(urlString);
        isValidSize(maxWidth, maxHeight);
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

}
