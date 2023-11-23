package nextstep.courses.domain;

public class Thumbnail {

    private final String name;
    private final String uri;
    private final Volume volume;
    private final Size size;

    public Thumbnail(String name,
                     String uri,
                     Volume volume,
                     Size size) {
        this.name = name;
        this.uri = uri;
        this.volume = volume;
        this.size = size;
    }
}
