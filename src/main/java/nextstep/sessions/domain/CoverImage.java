package nextstep.sessions.domain;

public class CoverImage {

    private String name;
    private ImageType type;
    private int size;
    private int width;
    private int height;
    private String path;

    public CoverImage(String name, int size, int width, int height, String path) {
        this.name = name;
        this.type = ImageType.valueOfName(name);
        this.size = size;
        this.width = width;
        this.height = height;
        this.path = path;
    }

}
