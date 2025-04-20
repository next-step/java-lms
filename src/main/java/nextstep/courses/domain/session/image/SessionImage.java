package nextstep.courses.domain.session.image;

import nextstep.common.domian.BaseDomain;
import nextstep.courses.domain.session.Session;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SessionImage extends BaseDomain {
    private static final double WIDTH_RATIO = 3;
    private static final double HEIGHT_RATIO = 2;
    private static final int MAX_BYTE_SIZE = 1024 * 1024;

    private final Session session;

    private final String url;

    private final ImageHandler imageHandler;

    private final SessionImageType type;

    public SessionImage(Session session, String url, ImageHandler imageHandler, SessionImageType type) throws IOException {
        this(null, false, session, url, imageHandler, type);
    }

    public SessionImage(String id, Session session, String url, ImageHandler imageHandler, SessionImageType type) throws IOException {
        this(id, false, session, url, imageHandler, type);
    }

    public SessionImage(String id, boolean deleted, Session session, String url, ImageHandler imageHandler, SessionImageType type) throws IOException {
        super(id);

        BufferedImage res = imageHandler.image(url);
        if ((WIDTH_RATIO * res.getHeight()) != (HEIGHT_RATIO * res.getWidth())) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2 이여야 합니다.");
        }
        if (imageHandler.byteSize(url) > MAX_BYTE_SIZE) {
            throw new IllegalArgumentException("크기가 1MB를 초과했습니다.");
        }

        this.session = session;
        this.url = url;
        this.imageHandler = imageHandler;
        this.type = type;
        this.deleted = deleted;
    }

    public String url() {
        return url;
    }

    public String type() {
        return type.getImage();
    }

    public BufferedImage image() throws IOException {
        return imageHandler.image(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionImage that = (SessionImage) o;
        return Objects.equals(url, that.url) && Objects.equals(imageHandler, that.imageHandler) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, imageHandler, type);
    }
}
