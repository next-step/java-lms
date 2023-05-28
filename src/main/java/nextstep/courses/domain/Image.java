package nextstep.courses.domain;

public class Image {

    public static final String DEFAULT_IMAGE_URL = "https://via.placeholder.com/350x150";
    private final String url;

    public Image() {
        this.url = DEFAULT_IMAGE_URL;
    }

    public Image(String url) {
        this.url = url;
    }
}
