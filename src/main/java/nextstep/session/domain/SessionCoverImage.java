package nextstep.session.domain;

import nextstep.session.InvalidImageConditionsException;

public class SessionCoverImage {


    private final int MAX_SIZE = 1024 * 1024;
    private Long id;
    private final Long session_id;
    private final ImageType imageType;
    private final Dimension dimension;
    private final long size;

    public SessionCoverImage(Long id, Long session_id, long width, long height, long size,
        String type)
        throws InvalidImageConditionsException {
        validate(size, type);
        this.id = id;
        this.session_id = session_id;
        this.dimension = new Dimension(width, height);
        this.size = size;
        this.imageType = ImageType.valueOf(type.toLowerCase());
    }

    public SessionCoverImage(Long session_id, long width, long height, long size, String type) {
        validate(size, type);
        this.session_id = session_id;
        this.dimension = new Dimension(width, height);
        this.size = size;
        this.imageType = ImageType.valueOf(type.toLowerCase());
    }

    private void validate(long size, String type) {
        if (isOverSize(size)) {
            throw new InvalidImageConditionsException(String.format("이미지 크기는"
                + "%dMB 이하여야합니다. 입력된 이미지 크기 %dMB", MAX_SIZE / (1024 * 1024), size / (1024 * 1024)));
        }
        if (!ImageType.validateType(type)) {
            throw new InvalidImageConditionsException(
                "명시된 확장자만 추가 가능합니다." + ImageType.nameConcat(","));
        }
    }

    private boolean isOverSize(long size) {
        return size > MAX_SIZE;
    }

    public boolean isSameImageType(String type) {
        return imageType.isSame(type);
    }

    public Long getSession_id() {
        return session_id;
    }

    public String getImageType() {
        return imageType.toString();
    }

    public long getWidth() {
        return dimension.getWidth();
    }

    public long getHeight() {
        return dimension.getHeight();
    }

    public long getSize() {
        return size;
    }
}
