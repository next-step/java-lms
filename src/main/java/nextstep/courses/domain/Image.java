package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Image {
    private Long id;

    private int size;

    private Type type;

    private int width;

    private int height;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Type {
        GIF, JPG, JPEG, PNG, SVG
    }

    public Image() {
    }

    public Image(int size, Type type, int width, int height) {
        this(0L, size, type, width, height, LocalDateTime.now(), null);
    }

    public Image(Long id, int size, Type type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
