package nextstep.lms.domain;

import java.util.Objects;

public class Image {
    private Long id;

    private String url;

    public static Image of(Long id, String url) {
        return new Image(id, url);
    }

    public static Image ofDefault() {
        return new Image(0L, "https://edu.nextstep.camp/images/covers/basic/008.jpg");
    }

    private Image(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
