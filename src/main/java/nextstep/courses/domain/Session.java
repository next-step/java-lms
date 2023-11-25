package nextstep.courses.domain;

public class Session {

    private Image image;

    public Session(Image image) {
        this.image = image;
    }

    public Image image() {
        return this.image;
    }

}
