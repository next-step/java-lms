package nextstep.session.domain.image;

import nextstep.DateDomain;

import java.time.LocalDateTime;

public class Image {

    private Long id;
    private final String name;
    private final ImageSize size;
    private final ImageCapacity capacity;
    private final DateDomain dateDomain;

    public Image(String name, int width, int height, int capacity) {
        ImageExtension.confirmImageExtension(name);
        this.name = name;
        this.size = new ImageSize(width, height);
        this.capacity = new ImageCapacity(capacity);
        this.dateDomain = new DateDomain();
    }

    public Image(Long id, String name, int width, int height, int capacity) {
        ImageExtension.confirmImageExtension(name);
        this.id = id;
        this.name = name;
        this.size = new ImageSize(width, height);
        this.capacity = new ImageCapacity(capacity);
        this.dateDomain = new DateDomain();
    }

    public Image(Long id, String name, int width, int height, int capacity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.size = new ImageSize(width, height);
        this.capacity = new ImageCapacity(capacity);
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageSize getSize() {
        return size;
    }

    public ImageCapacity getCapacity() {
        return capacity;
    }

    public DateDomain getDateDomain() {
        return dateDomain;
    }
}
