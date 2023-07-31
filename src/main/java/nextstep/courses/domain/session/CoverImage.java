package nextstep.courses.domain.session;

import java.util.Objects;

public class CoverImage {
    private String path;
    private String name;

    public CoverImage(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(path, that.path) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name);
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
