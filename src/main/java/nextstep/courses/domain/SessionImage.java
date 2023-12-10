package nextstep.courses.domain;

import nextstep.courses.enumeration.ExtensionType;
import nextstep.courses.exception.ExceedImageRatioException;
import nextstep.courses.exception.ExceedImageSizeException;
import nextstep.courses.exception.ExceedImageWidthHeightException;

import java.time.LocalDateTime;

public class SessionImage extends BaseEntity {

    private static final Integer ONE_MB = 1024 * 1024;
    private static final Integer MINIMUM_WIDTH = 300;
    private static final Integer MINIMUM_HEIGHT = 200;
    private static final Integer WIDTH_RATIO = 3;
    private static final Integer HEIGHT_RATIO = 2;

    private final Long id;
    private final Long sessionId;
    private final String imageUrl;
    private final ExtensionType extensionType;
    private final Long size;

    private SessionImage(Long id, Long sessionId, String imageUrl, ExtensionType extensionType, Long size, Long width, Long height) {
        super(LocalDateTime.now(), LocalDateTime.now());

        validate(size, width, height);
        this.id = id;
        this.sessionId = sessionId;
        this.imageUrl = imageUrl;
        this.extensionType = extensionType;
        this.size = size;
    }

    private SessionImage(Long id, Long sessionId, String imageUrl, ExtensionType extensionType, Long size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);

        this.id = id;
        this.sessionId = sessionId;
        this.imageUrl = imageUrl;
        this.extensionType = extensionType;
        this.size = size;
    }

    public static SessionImage of(Long id, Long sessionId, String imageUrl, ExtensionType extensionType, Long size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new SessionImage(id, sessionId, imageUrl, extensionType, size, createdAt, updatedAt);
    }

    public static SessionImage of(Long id, Long sessionId, String imageUrl, String extensionType, Long size, Long width, Long height) {
        return new SessionImage(id, sessionId, imageUrl, ExtensionType.findType(extensionType), size, width, height);
    }

    private void validate(long size, long width, long height) {
        if (size > ONE_MB) {
            throw new ExceedImageSizeException("이미지 사이즈는 최대 1MB 입니다. 현재사이즈 : " + size);
        }

        if (width < MINIMUM_WIDTH || height < MINIMUM_HEIGHT) {
            throw new ExceedImageWidthHeightException("이미지 최소 넓이는 " + MINIMUM_WIDTH + "px, 높이는 최소 " + MINIMUM_HEIGHT + "입니다.");
        }

        if (!validRatio(width, height)) {
            throw new ExceedImageRatioException("이미지 넓이와 높이의 비율은 " + WIDTH_RATIO + " : " + HEIGHT_RATIO + "입니다.");
        }
    }

    private boolean validRatio(long width, long height) {
        return width * HEIGHT_RATIO == height * WIDTH_RATIO;
    }

    public long getId() {
        return this.id;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public ExtensionType getExtensionType() {
        return this.extensionType;
    }

    public long getSize() {
        return this.size;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
