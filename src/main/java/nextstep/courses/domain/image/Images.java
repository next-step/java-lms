package nextstep.courses.domain.image;

import java.util.List;

public class Images {
    private final List<Image> value;

    public Images(List<Image> value) {
        this.value = value;
    }

    public List<Image> getValue() {
        return this.value;
    }
}
