package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;

public class SessionImage {
    public static int LIMIT_IMAGE_SIZE = 1024 * 1024; // 1MB
    public static int MIN_IMAGE_WIDTH_IN_PIXELS = 300;
    public static int MIN_IMAGE_HEIGHT_IN_PIXELS = 200;
    public static double ASPECT_RATIO = (double) MIN_IMAGE_WIDTH_IN_PIXELS / MIN_IMAGE_HEIGHT_IN_PIXELS;

    private Long id;

    private String name;

    private long sessionId;

    private int imageSize;

    private int width;

    private int height;

    private ImageType imageType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static SessionImage valueOf(long id, Session session, int size, int width, int height, String imageType) throws InvalidImageFormatException {
        return new SessionImage(id, "tmp", session.getSessionId(), size, width, height, ImageType.validateImageType(imageType), LocalDateTime.now(), null);
    }

    public SessionImage(String name, long sessionId, int size, int width, int height, ImageType imageType) throws InvalidImageFormatException {
        this(0L, name, sessionId, size, width, height, imageType, LocalDateTime.now(), null);
    }
    public SessionImage(Long id, String name, long sessionId, int size, int width, int height, ImageType imageType, LocalDateTime createdAt, LocalDateTime updatedAt) throws InvalidImageFormatException {
        validateImageFormat(size, width, height);

        this.id = id;
        this.name = name;
        this.sessionId = sessionId;
        this.imageSize = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void validateImageFormat(int size, int width, int height) throws InvalidImageFormatException {
        validateImageSize(size);
        validateWidthHeight(width, height);
    }

    private void validateWidthHeight(int width, int height) throws InvalidImageFormatException {
        if (width < MIN_IMAGE_WIDTH_IN_PIXELS || width <= 0) {
            throw new InvalidImageFormatException("이미지 가로는 300픽셀 이상이어야 합니다.");
        }
        if (height < MIN_IMAGE_HEIGHT_IN_PIXELS || height <= 0) {
            throw new InvalidImageFormatException("이미지 세로는 200픽셀 이상이어야 합니다.");
        }
        if ((double) width / height != ASPECT_RATIO) {
            throw new InvalidImageFormatException("이미지 가로 세로 비율은 3:2여야 합니다.");
        }
    }

    private void validateImageSize(int imageSize) throws InvalidImageFormatException {
        if (imageSize > LIMIT_IMAGE_SIZE) {
            throw new InvalidImageFormatException("이미지 크기는 1MB 이하만 가능합니다.");
        }
        if (imageSize <= 0) {
            throw new InvalidImageFormatException("이미지 크기는 0MB 이하로는 불가능합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public long getSessionId() {
        return sessionId;
    }

    public int getImageSize() {
        return imageSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getImageType() {
        return imageType.name();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "SessionImage{" +
                "imageSize=" + imageSize +
                ", width=" + width +
                ", height=" + height +
                ", imageType=" + imageType +
                '}';
    }
}
