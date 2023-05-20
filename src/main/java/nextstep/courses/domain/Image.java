package nextstep.courses.domain;

import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.exception.InvalidImageException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Image extends BaseEntity {
    private static final Long MAX_SIZE = 5L * 1024 * 1024; // 5MB

    private Long id;

    private String name;

    private URI uri;

    private Long size;

    private ImageType type;

    protected Image() {
    }

    public static Image create(String name, String uri, Long size, ImageType type) throws InvalidImageException, URISyntaxException {
        validateUri(uri);
        validateSize(size);
        validateType(type);

        Image image = new Image();
        image.name = name;
        image.uri = new URI(uri);
        image.size = size;
        image.type = type;

        return image;
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
        } catch (URISyntaxException | MalformedURLException e) {
            throw new InvalidImageException("유효하지 않은 이미지 URI입니다.");
        }

    }

    private static void validateSize(Long size) throws InvalidImageException {
        if (size > MAX_SIZE) {
            throw new InvalidImageException("이미지의 크기가 너무 큽니다. 최대 ");
        }
    }

    private static void validateType(ImageType type) throws InvalidImageException {
        if (!ImageType.canUpload(type)) {
            throw new InvalidImageException("지원하지 않는 이미지 형식입니다.");
        }
    }

}
