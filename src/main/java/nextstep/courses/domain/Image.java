package nextstep.courses.domain;

import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.exception.InvalidImageException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Image extends BaseEntity {
    private static final Long MAX_SIZE = 5L * 1024 * 1024; // 5MB

    private Long id;

    private String name;

    private URI uri;

    private Long size;

    private ImageType type;

    protected Image() {
    }

    public Image(Long id, String name, URI uri, Long size, ImageType type) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.size = size;
        this.type = type;
    }

    public Image(Long id, String name, URI uri, Long size, ImageType type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.size = size;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Image create(String name, String uri, Long size, String typeString) throws InvalidImageException, URISyntaxException {
        validateUri(uri);
        validateSize(size);

        Image image = new Image();
        image.name = name;
        image.uri = new URI(uri);
        image.size = size;
        image.type = ImageType.of(typeString);

        return image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public URI getUri() {
        return uri;
    }

    public Long getSize() {
        return size;
    }

    public ImageType getType() {
        return type;
    }

    public static Long getMaxSize() {
        return MAX_SIZE;
    }

    private static void validateUri(String uri) throws InvalidImageException {
        try {
            new URI(uri).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new InvalidImageException("유효하지 않은 이미지 URI입니다.");
        }
    }

    private static void validateSize(Long size) throws InvalidImageException {
        if (size > MAX_SIZE) {
            throw new InvalidImageException("이미지의 크기가 너무 큽니다. 최대 " + MAX_SIZE + "바이트까지 허용됩니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(name, image.name) && Objects.equals(uri, image.uri) && Objects.equals(size, image.size) && type == image.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uri, size, type);
    }
}
