package nextstep.courses.domain;

import java.util.Arrays;

public class SessionCoverImage {
    private final byte[] image;

    public SessionCoverImage(byte[] image) {
        this.image = image;
    }

    public byte[] image() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionCoverImage)) return false;
        SessionCoverImage that = (SessionCoverImage) o;
        return Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }
}
