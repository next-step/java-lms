package nextstep.courses.domain.session;

import nextstep.courses.dto.CoverImageDto;

public class CoverImage {

    private static final int MAX_IMG_SIZE_BYTE = 1048576;

    private String path;
    private int fileSize;
    private ImageType imageType;
    private Size size;

    private CoverImage(String path, int fileSize, ImageType imageType, Size size) {
        this.path = path;
        this.fileSize = fileSize;
        this.imageType = imageType;
        this.size = size;
    }

    public static CoverImage from(CoverImageDto dto) {
        if (dto.getSize() > MAX_IMG_SIZE_BYTE || dto.getSize() <= 0) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하입니다.");
        }
        ImageType imageType = ImageType.findType(dto.getImgType());
        Size size = new Size(dto.getWidth(), dto.getHeight());
        return new CoverImage(dto.getPath(), dto.getSize(), imageType, size);
    }
}
