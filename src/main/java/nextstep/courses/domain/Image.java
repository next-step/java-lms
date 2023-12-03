package nextstep.courses.domain;

public class Image {

    private final Long id;

    private final ImageInformation information;

    public Image(Long id, ImageInformation information) {
        this.id = id;
        this.information = information;
    }
}
