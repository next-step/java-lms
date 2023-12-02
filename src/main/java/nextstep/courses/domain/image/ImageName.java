package nextstep.courses.domain.image;

public class ImageName {

    private final String name;

    public ImageName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches("^.*\\.(gif|jpe?g|png|svg)$")) {
            throw new IllegalArgumentException("Invalid file extension.");
        }
    }

    public String name() {
        return name;
    }
}
