package nextstep.courses.domain;

public class ImageInfo {
    private String type;

    private Long size;

    private int width;

    private int height;

    @Override
    public String toString() {
        return "CoverImage{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
