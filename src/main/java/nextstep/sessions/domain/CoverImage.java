package nextstep.sessions.domain;

public class CoverImage {

    private final String name;
    private final ImageType type;
    private final ImageSize size;
    private final String path;

    public CoverImage(String name, int size, int width, int height, String path) {
        this.type = ImageType.valueOfName(name);
        this.name = name;
        this.size = new ImageSize(size, width, height);
        this.path = path;
    }

}
