package nextstep.session.domain;

import nextstep.session.InvalidImageConditionsException;

public class SessionCoverImage {


    private final int MAX_SIZE = 1024 * 1024;
    private final ImageType imageType;
    private final Dimension dimension;
    private final long size;

    public SessionCoverImage(long width, long height, long size, String type)
        throws InvalidImageConditionsException {
        validate(size, type);
        this.dimension = new Dimension(width, height);
        this.size = size;
        this.imageType = ImageType.valueOf(type.toLowerCase());
    }

    private void validate(long size, String type) throws InvalidImageConditionsException {
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

}
