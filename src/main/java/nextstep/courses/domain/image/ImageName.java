package nextstep.courses.domain.image;

import nextstep.courses.exception.ImageNameTypeException;

import java.util.Objects;

public class ImageName {

    private static final String IMAGE_EXTENSION_REGEX = "^.*\\.(gif|jpe?g|png|svg)$";
    private final String name;

    public ImageName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches(IMAGE_EXTENSION_REGEX)) {
            throw new ImageNameTypeException();
        }
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ImageName name1 = (ImageName) obj;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static ImageName of(String name) {
        return new ImageName(name);
    }
}
