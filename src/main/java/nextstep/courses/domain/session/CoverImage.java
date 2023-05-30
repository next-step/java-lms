package nextstep.courses.domain.session;

import java.util.Objects;

public class CoverImage {

    private final Long id;

    private final String imagePath;

    public CoverImage(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public CoverImage(String imagePath) {
        this.id = 0L;
        this.imagePath = imagePath;
    }

    public static CoverImage of(Long id, String imagePath) {
        return new CoverImage(id, imagePath);
    }

    public static CoverImage of(String imagePath) {
        return new CoverImage(imagePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoverImage that = (CoverImage) o;
        return Objects.equals(id, that.id) && Objects.equals(imagePath,
            that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imagePath);
    }

    public String imagePath() {
        return imagePath;
    }

    public Long id() {
        return id;
    }
}
