package nextstep.courses.domain;

import java.util.Map;

public class Session {

    private Long id;

    private Image coverImage;

    public void setCoverImage(Image image) {
        this.coverImage = image;
    }

    public Image getImage() {
        return this.coverImage;
    }
}
