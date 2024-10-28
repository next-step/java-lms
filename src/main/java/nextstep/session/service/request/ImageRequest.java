package nextstep.session.service.request;

import nextstep.session.domain.image.Image;

public class ImageRequest {

    private final String name;
    private final int width;
    private final int height;
    private final int capacity;

    public ImageRequest( String name, int width, int height, int capacity) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.capacity = capacity;
    }

    public Image toDomain() {
        return new Image(name, width, height, capacity);
    }
}
