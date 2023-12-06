package nextstep.courses.domain.session;

import nextstep.courses.dto.CoverImgDto;

public class CoverImg {

    private static final int MAX_IMG_SIZE_BYTE = 1048576;

    private String path;
    private int fileSize;
    private ImgType imgType;
    private Size size;

    private CoverImg(String path, int fileSize, ImgType imgType, Size size) {
        this.path = path;
        this.fileSize = fileSize;
        this.imgType = imgType;
        this.size = size;
    }

    public static CoverImg from(CoverImgDto dto) {
        if (dto.getSize() > MAX_IMG_SIZE_BYTE || dto.getSize() <= 0) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하입니다.");
        }
        ImgType imgType = ImgType.findType(dto.getImgType());
        Size size = new Size(dto.getWidth(), dto.getHeight());
        return new CoverImg(dto.getPath(), dto.getSize(), imgType, size);
    }
}
