package nextstep.courses.domain.image;

import java.util.Objects;

public class Image {

    private final Long id;

    private final Long sessionId;

    private final ImageInformation information;

    public Image(Long id,
                 Long sessionId,
                 ImageInformation information) {
        this.id = id;
        this.sessionId = sessionId;
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id)
                && Objects.equals(sessionId, image.sessionId)
                && Objects.equals(information, image.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, information);
    }
}
