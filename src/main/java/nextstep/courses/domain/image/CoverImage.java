package nextstep.courses.domain.image;

import nextstep.courses.domain.image.constant.ImageType;

public class CoverImage {

    public static final double MAX_FILE_SIZE = 1024 * 1024; // 1MB

    private Long id;
    private final long sessionId;
    private final long size;
    private final ImageType type;
    private final ImageDimentsion dimentsion;

    public CoverImage(long size, String type, int width, int height) {
        this(size, ImageType.fromName(type.toUpperCase()), width, height);
    }

    public CoverImage(Long id, Long sessionId, long size, String type, int width, int height) {
        this(id, sessionId, size, ImageType.fromName(type.toUpperCase()), width, height);
    }

    public CoverImage(long size,  ImageType type, int width, int height) {
        this(0L, 0L, size, type, new ImageDimentsion(width, height));
    }

    public CoverImage(Long id, Long sessionId,long size, ImageType type, int width, int height) {
        this(id, sessionId, size, type, new ImageDimentsion(width, height));
    }

    public CoverImage(Long id, Long sessionId, long size, ImageType type, ImageDimentsion dimentsion) {
        validateFileSize(size);
        this.id = id;
        this.sessionId = sessionId;
        this.size = size;
        this.type = type;
        this.dimentsion = dimentsion;
    }

    private void validateFileSize(long size) {
        if (size > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("이미지 사이즈는 1MB 이하만 가능합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

    public ImageType getType() {
        return type;
    }

    public int getWidth(){
        return this.dimentsion.getWidth();
    }

    public int getHeight(){
        return this.dimentsion.getHeight();
    }

}
