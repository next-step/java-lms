package nextstep.courses.domain.session.image;

import lombok.Getter;
import nextstep.common.domian.BaseDomain;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class SessionImage extends BaseDomain {
    private static final double WIDTH_RATIO = 3;
    private static final double HEIGHT_RATIO = 2;
    private static final int MAX_BYTE_SIZE = 1024 * 1024;

    private final String url;

    private final SessionImageType type;

    public SessionImage(String url, SessionImageType type) throws IOException {
        this(null, false, url, type);
    }

    public SessionImage(String id, boolean deleted, String url, SessionImageType type) throws IOException {
        super(id);
        this.url = url;
        this.type = type;
        this.deleted = deleted;

        if ((WIDTH_RATIO * height()) != (HEIGHT_RATIO * width())) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2 이여야 합니다.");
        }

        if (byteSize() > MAX_BYTE_SIZE) {
            throw new IllegalArgumentException("크기가 1MB를 초과했습니다.");
        }
    }

    public int height() throws IOException {
        return ImageIO.read(new URL(url)).getHeight();
    }

    public int width() throws IOException {
        return ImageIO.read(new URL(url)).getWidth();
    }

    public long byteSize() {
        try {
            URLConnection connection = new URL(url).openConnection();
            return connection.getContentLengthLong();
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 URL을 확인할 수 없습니다.");
        }
    }

    public void delete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SessionImage that = (SessionImage) o;
        return Objects.equals(url, that.url) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), url, type);
    }
}
